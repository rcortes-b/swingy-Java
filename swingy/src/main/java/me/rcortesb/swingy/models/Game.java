package me.rcortesb.swingy.models;
import me.rcortesb.swingy.models.heroes.*;
import me.rcortesb.swingy.models.villains.Villain;
import me.rcortesb.swingy.controller.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
	Controller controller;
	int map_size;
	Hero hero;
	GameMap hero_pos;
	List<GameMap> villains_pos;
	boolean	isFinished;

	public Game (Hero selectedHero) {
		System.out.println("Game address: " + this);
		this.isFinished = false;
		this.hero = selectedHero;
		this.villains_pos = new ArrayList<>();
		this.controller = Controller.getController();
		this.loadMap();
	}

	private void loadMap() {
		this.map_size = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
		this.hero_pos = new GameMap(map_size / 2);
		this.generateVillainsCells();
	}

	/*	Villains are in a 20% of the Map
		MAP SIZES: 9, 15, 19, 25, 29, 35, 39, ... +6 -> +4 -> +6 -> ... 
		For each row 2 villains are generated
	*/
	private void generateVillainsCells() {
		List<Integer> row_values = new ArrayList<>();
		int amount = (this.map_size * this.map_size) / 100 * 20;
		System.out.println("AMOUNT: " + amount);
		int first = 0;
		int random = 0;
		amount /= this.map_size;
		if (amount == 0)
			amount = 2;
		for (int y = 1; y < this.map_size - 1; y++) {
			first = (int)((Math.random() * (this.map_size - 2))) + 1;
			row_values.add(first);
			villains_pos.add(new GameMap(first, y));
			for (int i = 1; i < amount; i++) {
				random = (int)((Math.random() * (this.map_size - 2))) + 1;
				if (row_values.contains(random) == false) {
					row_values.add(random);
					villains_pos.add(new GameMap(random, y));
				} else {
					i--;
				}
			}
			row_values.removeAll(row_values);
		}
	}

	public boolean containVillain() {
		for (GameMap map : villains_pos) {
			if (hero_pos.matchCoords(map) == true) {
				villains_pos.remove(map);
				return true;
			}
		}
		return false;
	}

	public void playTurn() {
		if (hero_pos.isBorder(this.map_size) == true) {
			this.endGame(true);
		}
		else {
			if (this.containVillain() == true) {
				Villain villain = controller.getGameModel().generateVillain(hero.getLevel());
				int exp = this.simulateBattle(hero, villain);
				if (hero.isAlive() == true) {
					hero.gainExperience(exp);
					controller.getGameModel().generateArtifact(hero);
				} else {
					this.endGame(false);
				}
			}
		}
	}

	/* Hero always attacks first and I want it that way :)*/
	public int simulateBattle(Hero hero, Villain villain) {
		final int heroDamage = hero.getDamage();
		final int villainDamage = villain.getAttack();
		final int heroResistance = hero.getResistance();
		final int villainResistance = villain.getDefense();

		while (hero.getHP() > 0 && villain.getHP() > 0) {
			villain.takeDamage(heroDamage - villainResistance);
			if (villain.getHP() > 0)
				hero.takeDamage(villainDamage - heroResistance);
		}
		Controller.getViewModel().displayBattleResult(hero, villain);
		return villain.giveExperience();
	}

	public void endGame(boolean isWin) {
		if (isWin == true) {
			if (controller.getGameModel().isDefaultName(hero.getName()) == false)
					controller.getDBHandler().updateHeroToDatabase(hero);
			else
				hero.setToDefault();
			controller.getViewModel().showVictory();
		} else {
			if (hero.getHP() <= 0) {
				if (controller.getGameModel().isDefaultName(hero.getName()) == false) {
					controller.getDBHandler().deleteHeroFromDatabase(hero.getName());
					controller.getGameModel().getHeroes().remove(hero);
				} else {
					hero.setToDefault();
				}
				controller.getViewModel().showDefeat();
			} else {
				controller.getViewModel().showExitFromGame(this);
			}
		}
	}

	public int getMapSize() {
		return this.map_size;
	}

	public GameMap getCurrentPosition() {
		return this.hero_pos;
	}

	public Hero getHero() {
		return this.hero;
	}

	public boolean gameIsFinished() {
		return this.isFinished;
	}

	public void setAsFinished() {
		this.isFinished = true;
	}
}