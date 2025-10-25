package me.rcortesb.swingy.views;
import me.rcortesb.swingy.controller.Controller;
public abstract class View {
	protected enum GameStatus {IN_MENU, IN_CREATE_HERO, IN_GAME, IN_BATTLE};

	public abstract String getView();
	public abstract void displayMenu();
	public abstract void displayGameOptions();
	public abstract void launchApp();
}