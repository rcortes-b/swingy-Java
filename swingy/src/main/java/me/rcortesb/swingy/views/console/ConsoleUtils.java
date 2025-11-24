package me.rcortesb.swingy.views.console;
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
					Controller.cleanupResources(0);
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

	public static void printValue(Hero hero, int i, int size) {
		int str_size = 0;
		String str = "";

		switch (i) {
			case 0:
				str = hero.getName();
				break;
			case 1:
				str = hero.getClassType();
				break;
			case 2:
				str = String.valueOf(hero.getLevel());
					break;
			case 3:
				str = String.valueOf(hero.getAttack());
					break;
			case 4:
				str = String.valueOf(hero.getDefense());
					break;
			case 5:
				str = String.valueOf(hero.getHP());
					break;
			default:
				for (int j = 0; j < size; j++)
					System.out.print("-");
				System.out.println("");
				return ;
		}
		System.out.print(" | " + str);
		str_size = size - str.length();
		for (int j = 0; j < str_size; j++)
			System.out.print(" "); 
	}
}

