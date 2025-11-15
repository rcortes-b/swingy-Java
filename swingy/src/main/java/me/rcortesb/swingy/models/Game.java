package me.rcortesb.swingy.models;
import me.rcortesb.swingy.models.heroes.*;
import java.util.ArrayList;
import java.util.List;

public class Game extends GameModel {
	int map_size;
	Hero hero;
	GameMap hero_pos;
	List<GameMap> villains_pos;

	public Game (GameModel model, Hero selectedHero) {
		super(model);
		this.hero = selectedHero;
		this.villains_pos = new ArrayList<>();
		this.loadMap();
		this.debugGame();
	}

	private void loadMap() {
		this.map_size = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
		this.hero_pos = new GameMap(map_size / 2);
		this.generateVillains();
	}

	/*	Villains are in a 20% of the Map
		MAP SIZES: 9, 15, 19, 25, 29, 35, 39, ... +6 -> +4 -> +6 -> ... 
		For each row 2 villains are generated
	*/
	private void generateVillains() {
		List<Integer> row_values = new ArrayList<>();
		int amount = (this.map_size * 2) / 100 * 20;
		int random = 0;
		amount /= this.map_size;
		if (amount == 0)
			amount = 2;
		System.out.println("AMOUNT: " + amount);
		for (int y = 0; y < this.map_size; y++) {
			villains_pos.add(new GameMap((int)(Math.random() * this.map_size), y));
			for (int i = 1; i < amount; i++) {
				random = (int)(Math.random() * this.map_size);
				if (row_values.contains(random) == false) {
					row_values.add(random);
					villains_pos.add(new GameMap(random, y));
				}
			}
			row_values.removeAll(row_values);
		}
	}

	public void debugGame() {
		System.out.println("Hero: " + hero.getName());
		System.out.println("Map size: " + this.map_size + "x" + this.map_size);
		for (GameMap villain : villains_pos) {
			System.out.println("Villain in pos: " + villain.x + "," + villain.y);
		}
	}

}