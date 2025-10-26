package me.rcortesb.swingy.controller;
import me.rcortesb.swingy.views.*;
public class Controller {
	private static Controller controller = new Controller();
	private static View viewController;
	private static GameStatus status = GameStatus.IN_MENU;  

	private Controller() {}

	public static Controller getController() {
		return controller;
	}
	public static void registerView(View view) {
		viewController = view;
	}
	public static void applyView(String viewUI, boolean launchMode) {
		if (launchMode == false)
			viewController.deleteUI();
		if (viewUI.equals("gui"))
			registerView(new GUI_View());
		else
			registerView(new Console_View());
		if (launchMode == true)
			viewController.launchApp();
		else {
			viewController.getView();
			viewController.loadUI(status);
		}
	}
	public static void setStatus(GameStatus mode) {
		status = mode;
	}
	public static View getView() {
		return viewController;
	}
}