package me.rcortesb.swingy.views;
import me.rcortesb.swingy.controller.*;
public abstract class View {
	//protected enum GameStatus {IN_MENU, IN_CREATE_HERO, IN_GAME, IN_BATTLE};

	public abstract String getView();
	public abstract void loadMenu();
	public abstract void loadMenu2();
	public abstract void displayGameOptions();
	public abstract void launchApp();
	public abstract void deleteUI();
	public void loadUI(GameStatus status) {
		switch (status) {
			case IN_MENU:
					System.out.println("View mode: " + this.getView());
					this.loadMenu();
					break ;
			case IN_CREATE_HERO:
					break ;
			case IN_GAME:
					break ;
			case IN_BATTLE:
		}
	}
}