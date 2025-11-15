package me.rcortesb.swingy.controller;
import me.rcortesb.swingy.views.*;
import me.rcortesb.swingy.models.*;
import me.rcortesb.swingy.models.heroes.*;
import me.rcortesb.swingy.db_backend.DBHandler;
import java.util.List;
import java.util.ArrayList;

import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

public class Controller {
	private static Controller controller = new Controller();
	private static DBHandler db_handler;
	private static ViewModel viewModel;
	private static GUI_View gui;
	private static Console_View console;
	private static GameStatus status;
	private static boolean	consoleMode;
	private static GameModel gameModel;

	/* Constructor */

	private Controller() {
		this.db_handler = null;
		this.viewModel = null;
		this.gui = null;
		this.console = null;
		this.status = GameStatus.IN_MENU;
		this.gameModel = null;
	}

	/* View */

	public static void launchApp(String mode) {
		if (mode.equals("gui")) {
			consoleMode = false;
			gui = new GUI_View();
			viewModel = gui;
		} else {
			consoleMode = true;
			console = new Console_View();
			viewModel = console;
		}
		viewModel.launch();
	}

	public static void changeView() {
		if (consoleMode == true) {
			if (gui == null)
				gui = new GUI_View();
			viewModel = gui;
				
			consoleMode = false;
		}
		else {
			if (console == null)
				console = new Console_View();
			viewModel = console;
			consoleMode = true;
		}
		viewModel.setView();
	}

	public boolean isConsoleMode() {
		return consoleMode;
	}

	/* Model and Database */

	public void addHero(Hero new_hero) {
		this.db_handler.addHeroToDatabase(new_hero);
		this.gameModel.getHeroes().add(new_hero);
	}

	/* General Functions */

	public static void cleanup(int exitCode) {
		if (gui != null)
			gui.deleteGUI();
		if (console != null)
			console.deleteConsole(consoleMode);
		System.exit(exitCode);
	}

	/* Getters */

	public static Controller getController() {
		return controller;
	}

	public static GameStatus getStatus() {
		return status;
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

		/* Setters */

	public static void setStatus(GameStatus mode) {
		status = mode;
	}

	public static void startGame(Hero hero) {
		new Game(gameModel, hero);
	}

}