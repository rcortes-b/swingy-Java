package me.rcortesb.swingy.db_backend;
import me.rcortesb.swingy.controller.Controller;
import me.rcortesb.swingy.models.*;
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
	private Statement st;

	public DBHandler() {
		db_connection = null;
		st = null;
	}

	public void deleteDB() {
		try {
			st.close();
			db_connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException Error: " + e.getMessage());
		}
	}

	public void loadDatabase() {
		final String url = "jdbc:postgresql://localhost:5432/swingy_db";
		final Properties props = new Properties();
		if (System.getenv("SWINGY_DB_USER") == null || System.getenv("SWINGY_DB_PASSWORD") == null) {
			System.out.println("Necessary environment variables are not defined SWINGY_DB_USER, SWINGY_DB_PASSWORD");
			Controller.cleanup(1);
		}
		props.setProperty("user", System.getenv("SWINGY_DB_USER"));
		props.setProperty("password", System.getenv("SWINGY_DB_PASSWORD"));
		try {
            Class.forName("org.postgresql.Driver");
			db_connection = DriverManager.getConnection(url, props);
            /*System.out.println("Debug: Connected to PostgreSQL version: " +
            	((connection.getMetaData().getDatabaseProductVersion());*/
			st = db_connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("❌ DB Connection error: " + e.getMessage());
        }
	}

	public void readOperation(GameModel gameModel) {
		if (db_connection == null && st == null)
			this.loadDatabase();
		String[] models = {"heroes", "villains", "artifacts"};
		try {
			ResultSet rs;
			for (String model : models) {
				rs = st.executeQuery("SELECT * FROM " + model);
				while (rs.next()) {
					if (model.equals("heroes")) {
						gameModel.getHeroes().add(new Hero(rs.getString("name"), rs.getString("classType"), rs.getInt("level"),
						rs.getInt("experience"), rs.getInt("attack"), rs.getInt("defense"), rs.getInt("hp")));
					}
					else if (model.equals("villains")) {
						gameModel.getVillains().add(new Villain(rs.getString("name"), rs.getString("classType"),
						rs.getInt("attack"), rs.getInt("defense"), rs.getInt("hp")));
					} else {
						gameModel.getArtifacts().add(new Artifact(rs.getString("name"), rs.getString("type"),
						rs.getInt("value"), rs.getString("description")));
					}
				}
				rs.close();
			}
		} catch (Exception e) {
			System.out.println("Error in read operation: " + e.getMessage());
		}
	}

	public void addHeroToDatabase(Hero hero) {
		try {
			String strInsert = "insert into heroes (name, classType, level, experience, attack, defense, hp) values ('";
			String strValues =  hero.getName() + "','" + hero.getClassType() + "',1,0," + hero.getAttack() + "," + hero.getDefense() + "," + hero.getHP();
			st.executeUpdate(strInsert + strValues + ")");
		} catch (Exception e) {
			System.out.println("Error: Value cannot be added. " + e.getMessage());
		}
	}

	public void updateHeroToDatabase(Hero hero) {
		try {
			String preStr = "update heroes set ";
			String postStr = " where name='" + hero.getName() + "'";
			String[] updateValue = {"level=" + hero.getLevel(),
									"experience=" + hero.getExperience(),
									"attack=" + hero.getAttack(),
									"defense=" + hero.getDefense(),
									"hp=" + hero.getHP()
									};
			for (int i = 0; i < 5; i++) {
				st.executeUpdate(preStr + updateValue[i] + postStr);
			}
		} catch (Exception e) {
			System.out.println("Error: Value cannot be updated. " + e.getMessage());
		}
	//update heroes set experience=100 where name='defaultWarrior' (st.execute())
	}
	//INSERT INTO heroes (name, classtype, level, experience, attackdmg, armordefense, helmhp) values ('pepe', 'lagarto', 1, 0, 100,200,300);
}

