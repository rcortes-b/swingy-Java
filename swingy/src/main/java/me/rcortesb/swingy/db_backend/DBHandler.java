package me.rcortesb.swingy.db_backend;
import me.rcortesb.swingy.controller.Controller;
import me.rcortesb.swingy.models.heroes.*;
import me.rcortesb.swingy.models.game.GameModel;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

/*
	How do I handle the DB?
		What's the best for: performance and security?
			- Security -> Don't let the DB open, crash/leak the connection may remain openned
			- Performance -> Creating a connection for every query or write operation is not optimal.
							 In this project I won't use a pool / proxy
		What have I done?
			I open a connection if a "list heroes" or a game is triggered and then run a read operation
			(store in Lists: heroes and to avoid openning a new connection I also store the default villains and artifacts)
			At this point not read operation will be required anymore.
			Whenever i create a hero or finish a game I run a write operation and update the List with the new hero or the hero values
			updated so I don't need to do another read operation.
*/
/*
	Why it is not a Singleton Pattern if I just need one instance?
		- Because db may not be required. Maybe you start the game but you not to play.
*/

public class DBHandler {
	private Connection db_connection;

	public DBHandler() {
		db_connection = null;
	}

	public void closeDB() {
		try {
			db_connection.close();
			db_connection = null;
		} catch (SQLException e) {
			Controller.handleError("Closing DB Connection unexpected error", false);
		}
	}

	public void loadDatabase() {
		final String url = "jdbc:postgresql://localhost:5432/swingy_db";
		final Properties props = new Properties();
		try {
			if (System.getenv("SWINGY_DB_USER") == null || System.getenv("SWINGY_DB_PASSWORD") == null)
				throw new SQLException();
			props.setProperty("user", System.getenv("SWINGY_DB_USER"));
			props.setProperty("password", System.getenv("SWINGY_DB_PASSWORD"));
            Class.forName("org.postgresql.Driver");
			db_connection = DriverManager.getConnection(url, props);
        } catch (ClassNotFoundException | SQLException e) {
			Controller.handleError("DB Connection unexpected error", false);
        }
	}

	public void readOperation(GameModel gameModel) {
		try {
			this.loadDatabase();
			if (db_connection == null)
				throw new Exception();
			final String strSQL = "SELECT * FROM heroes";
			PreparedStatement ps = db_connection.prepareStatement(strSQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Hero hero;
				String classType = rs.getString("classType");
				if (classType.equals("Warrior")) {
					hero = new Warrior(rs.getString("name"), rs.getInt("level"),
					rs.getInt("experience"), rs.getInt("attack"), rs.getInt("defense"), rs.getInt("hp"));
				}
				else if (classType.equals("Wizard")) {
					hero = new Wizard(rs.getString("name"), rs.getInt("level"),
					rs.getInt("experience"), rs.getInt("attack"), rs.getInt("defense"), rs.getInt("hp"));
				}
				else {
					hero = new Healer(rs.getString("name"), rs.getInt("level"),
					rs.getInt("experience"), rs.getInt("attack"), rs.getInt("defense"), rs.getInt("hp"));
				}
				if (gameModel.validateHero(hero, null) == true)
					gameModel.getHeroes().add(hero);
			}
			rs.close();
			ps.close();
			this.closeDB();
		} catch (Exception e) {
			Controller.handleError("Error loading data from the database", false);
		}
	}

	public void addHeroToDatabase(Hero hero) {
		try {
			this.loadDatabase();
			if (db_connection == null)
				throw new Exception();
			final String strSQL = "INSERT INTO heroes (name, classType, level, experience, attack, defense, hp) VALUES (?, ?, 1, 0, ?, ?, ?)";
			PreparedStatement ps = db_connection.prepareStatement(strSQL);
			ps.setString(1, hero.getName());
			ps.setString(2, hero.getClassType());
			ps.setInt(3, hero.getAttack());
			ps.setInt(4, hero.getDefense());
			ps.setInt(5, hero.getHP());
			ps.executeUpdate();
			ps.close();
			this.closeDB();
		} catch (Exception e) {
			Controller.handleError("Error: Hero cannot be added to database", false);
		}
	}

	public void updateHeroToDatabase(Hero hero) {
		try {
			this.loadDatabase();
			if (db_connection == null)
				throw new Exception();
			final String strSQL = "UPDATE heroes SET level=?, experience=?, attack=?, defense=?, hp=? WHERE name=?";
			PreparedStatement ps = db_connection.prepareStatement(strSQL);
			ps.setInt(1, hero.getLevel());
			ps.setInt(2, hero.getExperience());
			ps.setInt(3, hero.getAttack());
			ps.setInt(4, hero.getDefense());
			ps.setInt(5, hero.getHP());
			ps.setString(6, hero.getName());
			ps.executeUpdate();
			ps.close();
			this.closeDB();
		} catch (Exception e) {
			Controller.handleError("Error: Hero stats cannot be updated to database", false);
		}
	}

	public void deleteHeroFromDatabase(String hero_name) {
		try {
			this.loadDatabase();
			if (db_connection == null)
				throw new Exception();
			final String strSQL = "DELETE FROM heroes WHERE name=?";
			PreparedStatement ps = db_connection.prepareStatement(strSQL);
			ps.setString(1, hero_name);
			ps.executeUpdate();
			ps.close();
			this.closeDB();
		} catch (Exception e) {
			Controller.handleError("Error: Hero cannot be deleted from database", false);
		}
	}
}

