package me.rcortesb.swingy.views;
import me.rcortesb.swingy.controller.*;
import me.rcortesb.swingy.models.*;
import me.rcortesb.swingy.models.heroes.Hero;
import me.rcortesb.swingy.models.villains.Villain;
import me.rcortesb.swingy.models.artifacts.Artifact;

public abstract class ViewModel {
	protected Controller controller = Controller.getController();

	public void launch() {
		if (controller.isConsoleMode() == true) {
			System.out.println("\n--------------------------------------------\n");
			System.out.println("Welcome to Achieve The Border!!");
			System.out.println("Currently you're in view_mode=console, type \"set view_mode=gui\" to change to a graphic interface");
		}
		this.loadMenu();
	}

	public void setView() {
		GameStatus status = controller.getStatus();
		switch (status) {
			case IN_MENU:
				this.loadMenu();
				break ;
			case IN_HERO_MENU:
				this.loadHeroMenu();
				break ;
			case IN_GAME_MENU:
				this.loadGameMenu();
				break ;
			case IN_GAME:
				this.loadGame();
				break ;
		}
	}
	public abstract void changeView();
	public abstract void loadMenu();
	public abstract void loadHeroMenu();
	public abstract void createHero();
	public abstract void listHeroes();
	public abstract void loadGameMenu();
	public abstract void loadGame();
	public abstract void showExitFromGame(Game game);
	public abstract void showVictory();
	public abstract void showDefeat();
	public abstract void showError(String msg);
	public abstract void showLevelUp(Hero hero);
	public abstract void showArtifactDropped(Artifact curr_item, Artifact new_item);
	public abstract void showArtifactAttached(Artifact item);
	public abstract void displayBattleResult(Hero hero, Villain villain);
}