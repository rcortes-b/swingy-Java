package me.rcortesb.swingy.views;
import me.rcortesb.swingy.controller.*;
import me.rcortesb.swingy.models.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Console_View implements ViewModel {
	private static Scanner myObj = new Scanner(System.in);
	private static Controller controller = Controller.getController();

	public void launchConsole() {
		System.out.println("\n--------------------------------------------\n");
		System.out.println("Welcome to Achieve The Border!!");
		System.out.println("Currently you're in view_mode=console, type \"set view_mode=gui\" to change to a graphic interface");
		loadMenu();
	}
	public void	setView() {
		GameStatus status = controller.getStatus();
		switch (status) {
			case IN_MENU:
				this.loadMenu();
				break ;
			case IN_HERO_MENU:
				System.out.println("Load in create hero");
				break ;
			case IN_GAME:
				System.out.println("Load in game");
				break ;
			case IN_BATTLE:
				System.out.println("Load in battle");
				break ;
		}
	}

	public void loadMenu() {
		try {
			controller.setStatus(GameStatus.IN_MENU);
			System.out.println("\nWhat do you want to do?");
			System.out.println("\t1 - Start game");
			System.out.println("\t2 - Create a hero");
			System.out.println("\t3 - Change to GUI mode");
			System.out.println("\t4 - Exit\n");
			System.out.print("Introduce option: ");
			String userInput = myObj.nextLine();
			handleMenuInput(userInput);
		} catch (Exception e) {
			controller.cleanup(1);
		}
	}
	
	public void loadCreateHero() {
		try {
			controller.setStatus(GameStatus.IN_HERO_MENU);
			System.out.println("\nWelcome to the hero factory!\n"); //Validations!!!!
			System.out.println("\t1 - Create a new hero");
			System.out.println("\t2 - List your heroes");
			System.out.println("\t3 - Change to GUI mode");
			System.out.println("\t4 - Go back to menu\n");
			System.out.print("Introduce option: ");
			String userInput = myObj.nextLine();
			handleHeroInput(userInput);
			//if all the values are correct, add hero to database and add hero to list<Heroes> from the Controller (same behaviour in GUI_View)

		} catch (Exception e) {
			controller.cleanup(1);
		}
	}
	public void printValue(Hero hero, int i, int size) {
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
	public void listHeroes() {
		try {
			List<Hero> heroes = controller.getGameModel().getHeroes();
			int[]		table_size = {15, 7, 3, 3, 3, 3};
			System.out.println("\n | name            | class   | lvl | atk | def | hp |");
			for (Hero hero : heroes) {
				printValue(null, 6, 53);
				for (int i = 0; i < 6; i++) {
					printValue(hero, i, table_size[i]);
				}
				System.out.println("|");
			}
			printValue(null, 6, 53);
			this.loadCreateHero();
		} catch (Exception e) {
			System.out.println("Error: Something went wrong listing the heroes\n" + e.getMessage());
		}
	}

	public void displayGameOptions() {
		System.out.println("\nGame Options:");
		System.out.println("\t- \"w\" -> Move 1 step to the North");
		System.out.println("\t- \"d\" -> Move 1 step to the East");
		System.out.println("\t- \"s\" -> Move 1 step to the South");
		System.out.println("\t- \"a\" -> Move 1 step to the West");
		System.out.println("\t- \"set view_mode=<gui,console>\" -> Change between user interfaces.");
		loadMenu();
	}

	public void deleteConsole(boolean print_msg) {
		myObj.close();
		if (print_msg)
			System.out.println("\n\nThanks for playing, we hope to see you soon!\n");
	}

	public void changeViewMode() {
		controller.loadGUI();
	}

	public void createHero() {
		String heroName, heroClass, attack, defense, hp;
		String[] input_msg = {"Name: ", "Hero Class (1 to Warrior, 2 to Wizard, 3 to Healer): ", "Attack Points: ",
						"Defense Points: ", "Heal Points: " };
		String[] value = new String[5];
		
		System.out.println("\nCustomize your hero as your like!\n");
		for (int i = 0; i < 5; i++) {
			System.out.print(input_msg[i]);
			value[i] = myObj.nextLine();
			if (i == 1 && !value[i].equals("1") && !value[i].equals("2") && !value[i].equals("3")) {
				System.out.println("Bad input: Class must be either Warrior, Wizard or Healer (1-3)");
				i = 0;
				continue ;
			}
			else if (i == 1) {
				if (value[i].equals("1"))
					value[i] = "Warrior";
				else if (value[i].equals("2"))
					value[i] = "Wizard";
				else
					value[i]  = "Healer";
			}
			System.out.println("");
		}
		controller.getGameModel().getHeroes().add(new Hero(value[0], value[1], 1, 0, Integer.parseInt(value[2]),
															Integer.parseInt(value[3]), Integer.parseInt(value[4])));
		// ADD THE NEW HERO TO THE DATABASE
		System.out.println("Hero has been succesfully created!\n");
		this.loadCreateHero();
	}

	private void handleMenuInput(String userInput) {
		try {
			if (userInput.equals("set view_mode=gui"))
				this.changeViewMode();
			else {
				int value = Character.getNumericValue(userInput.charAt(0));
				if (userInput.length() != 1 || (value < 1 || value > 4)) {
					if (userInput.equals("set view_mode=console"))
						System.out.println("\nYou already are in console mode!");
					throw new Exception();
				}
				else {
					switch (value) {
						case 1:
								System.out.println("Here goes the game");
								break;
						case 2:
								this.loadCreateHero();
								break;
						case 3:
								this.changeViewMode();
								break;
						case 4:
								controller.cleanup(0);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error: Input must be either 1, 2, 3, 4 or \"set view_mode=gui\"\n");
			loadMenu();
		}
	}

	private void handleHeroInput(String userInput) {
		try {
			if (userInput.equals("set view_mode=gui"))
				this.changeViewMode();
			else {
				int value = Character.getNumericValue(userInput.charAt(0));
				if (userInput.length() != 1 || (value < 1 || value > 4)) {
					if (userInput.equals("set view_mode=console"))
						System.out.println("\nYou already are in console mode!");
					throw new Exception();
				}
				else {
					switch (value) {
						case 1:
								this.createHero();
								break;
						case 2:
								this.listHeroes();
								break;
						case 3:
								this.changeViewMode();
								break;
						case 4:
								this.loadMenu();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error: Input must be either 1, 2, 3, 4 or \"set view_mode=gui\"\n");
			loadMenu();
		}
	}
}