package me.rcortesb.swingy.views;
import me.rcortesb.swingy.controller.*;
import me.rcortesb.swingy.models.heroes.Hero;
import java.util.List;

public class ConsoleUtils {
	private ConsoleUtils() {}

	public static void setOptions(int value, Console_View console) {
		GameStatus status = Controller.getStatus();

		if (status == GameStatus.IN_MENU)
			setMenuOptions(value, console);
		else if (status == GameStatus.IN_HERO_MENU)
			setHeroOptions(value, console);
		else
			setGameOptions(value, console);
	}

	public static void setMenuOptions(int value, Console_View console) {
		switch (value) {
			case 1:
					console.loadGameMenu();
					break;
			case 2:
					console.loadHeroMenu();
					break;
			case 3:
					console.changeView();
					break;
			case 4:
					Controller.cleanup(0);
		}
	}

	public static void setHeroOptions(int value, Console_View console) {
		switch (value) {
			case 1:
					console.createHero();
					break;
			case 2:
					console.listHeroes();
					break;
			case 3:
					console.changeView();
					break;
			case 4:
					console.loadMenu();
		}
	}

	public static void setGameOptions(int value, Console_View console) {
		switch (value) {
			case 1:
					console.listHeroes();
					break;
			case 2:
					console.createHero();
					break;
			case 3:
					console.changeView();
					break;
			case 4:
					console.loadMenu();
		}
	}

	public static Hero handleHeroSelection(List<Hero> heroes, String name) {
		for (Hero hero : heroes) {
			if (name.equals(hero.getName()))
				return hero;
		}
		return null;
	}
}

