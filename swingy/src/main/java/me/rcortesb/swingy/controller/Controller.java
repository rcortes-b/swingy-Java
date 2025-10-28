package me.rcortesb.swingy.controller;
import me.rcortesb.swingy.views.*;
import me.rcortesb.swingy.models.*;
import java.util.List;
import java.util.ArrayList;

import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

public class Controller {
	private static Controller controller = new Controller();
	private static GUI_View gui;
	private static Console_View console;
	private static GameStatus status;  
	private static boolean	consoleMode;
	private static List<Hero> heroes;

	private Controller() {
		this.gui = null;
		this.console = null;
		this.status = GameStatus.IN_MENU;
		this.heroes = null;
 
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

	public static List<Hero> getHeroes() {
		if (heroes == null) {
			final String url = "jdbc:postgresql://localhost:5432/postgres";
			final Properties props = new Properties();
			props.setProperty("user", "defaultUser");
			props.setProperty("password", "defaultPassword");
			try {
            // Force-load the PostgreSQL driver
            Class.forName("org.postgresql.Driver");

            try (Connection conn = DriverManager.getConnection(url, props)) {
                System.out.println("Connected to PostgreSQL version: " +
                    conn.getMetaData().getDatabaseProductVersion());
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ PostgreSQL JDBC Driver not found on classpath.");
        } catch (SQLException e) {
            System.err.println("❌ SQL error: " + e.getMessage());
            e.printStackTrace();
        }
		}
		return heroes;
	}
}