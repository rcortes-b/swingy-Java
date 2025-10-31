package me.rcortesb.swingy.controller;
import me.rcortesb.swingy.views.*;
import me.rcortesb.swingy.models.*;
import me.rcortesb.swingy.db_backend.DBHandler;
import java.util.List;
import java.util.ArrayList;

import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

public class Controller {
	private static Controller controller = new Controller();
	private static DBHandler db_handler;
	private static GUI_View gui;
	private static Console_View console;
	private static GameStatus status;  
	private static boolean	consoleMode;
	private static GameModel gameModel;

	private Controller() {
		this.db_handler = null;
		this.gui = null;
		this.console = null;
		this.status = GameStatus.IN_MENU;
		this.gameModel = null;
	}

	public static Controller getController() {
		return controller;
	}

	public static GameModel getGameModel() {
		if (gameModel == null) {
			gameModel = new GameModel();
		}
		return gameModel;
	}

	public static GUI_View getGUI() {
		return gui;
	}

	public static Console_View getConsole() {
		return console;
	}

	public static DBHandler getDBHandler() {
		if (db_handler == null)
			db_handler = new DBHandler();
		return db_handler;
	}

	public static void loadConsole(boolean launchMode) {
		consoleMode = true; 
		if (console == null)
			console = new Console_View();
		if (launchMode == true)
			console.launchConsole();
		else
			console.setView();
	}
	
	public static void loadGUI() {
		consoleMode = false;
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

	public static void cleanup(int exitCode) {
		if (gui != null)
			gui.deleteGUI();
		if (console != null)
			console.deleteConsole(consoleMode);
		System.exit(exitCode);
	}
}