package me.rcortesb.swingy.controller;
import me.rcortesb.swingy.views.View;
public class Controller {
	public enum GameStatus {IN_MENU, IN_CREATE_HERO, IN_GAME, IN_BATTLE}
	private static Controller controller = new Controller();
	private static View viewController;
	private static GameStatus status;  

	private Controller() {}

	public static Controller getController() {
		return controller;
	}
	public static void registerView(View view) {
		viewController = view;
	}
	public static View getView() {
		return viewController;
	}
}