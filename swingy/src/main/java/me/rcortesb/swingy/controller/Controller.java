package me.rcortesb.swingy.controller;
import me.rcortesb.swingy.views.*;
public class Controller {
	private static Controller controller = new Controller();
	private static GUI_View gui;
	private static Console_View console;
	private static GameStatus status;  

	private Controller() {
		this.gui = null;
		this.console = null;
		this.status = GameStatus.IN_MENU;
	}

	public static Controller getController() {
		return controller;
	}
	
	public static GUI_View getGUI() {
		return gui;
	}

	public static Console_View getConsole() {
		return console;
	}

	public static void loadConsole(boolean launchMode) {
		if (console == null)
			console = new Console_View();
		if (launchMode == true)
			console.launchConsole();
		else
			console.setView();
	}
	
	public static void loadGUI() {
		if (gui == null)
			gui = new GUI_View();
		gui.setView();
	}

	public static void setStatus(GameStatus mode) {
		status = mode;
	}
	public static GameStatus getStatus() {
		return status;
	}
}