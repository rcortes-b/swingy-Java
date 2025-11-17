package me.rcortesb.swingy.views;
import me.rcortesb.swingy.controller.*;
import me.rcortesb.swingy.models.*;
import me.rcortesb.swingy.models.heroes.*;
import me.rcortesb.swingy.models.villains.Villain;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Console_View extends ViewModel {
	private static Scanner myObj = new Scanner(System.in);

	/* Key Functions */

	public void deleteConsole(boolean print_msg) {
		myObj.close();
		if (print_msg)
			System.out.println("\n\nThanks for playing, we hope to see you soon!\n");
	}

	public void changeView() {
		controller.changeView();
	}

	/* Load Views */

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
			handleInput(userInput);
		} catch (Exception e) {
			controller.cleanup(1);
		}
	}

	public void loadHeroMenu() {
		try {
			controller.setStatus(GameStatus.IN_HERO_MENU);
			System.out.println("\nWelcome to the hero factory!\n"); //Validations!!!!
			System.out.println("\t1 - Create a new hero");
			System.out.println("\t2 - List your heroes");
			System.out.println("\t3 - Change to GUI mode");
			System.out.println("\t4 - Go back to menu\n");
			System.out.print("Introduce option: ");
			String userInput = myObj.nextLine();
			handleInput(userInput);
			//if all the values are correct, add hero to database and add hero to list<Heroes> from the Controller (same behaviour in GUI_View)

		} catch (Exception e) {
			controller.cleanup(1);
		}
	}

	public void loadGameMenu() {
		try {
			controller.setStatus(GameStatus.IN_GAME_MENU);
			System.out.println("\nBegin the game selecting a hero or creating one");
			System.out.println("\t1 - Select Hero");
			System.out.println("\t2 - Create Hero");
			System.out.println("\t3 - Change to GUI mode");
			System.out.println("\t4 - Go Back To Menu\n");
			System.out.print("Introduce option: ");
			String userInput = myObj.nextLine();
			handleInput(userInput);
		} catch (Exception e) {
			controller.cleanup(1);
		}
	}

	public void createHero() {
		String heroName, heroClass, attack, defense, hp;
		String[] input_msg = {"Name: ", "Hero Class: "};
		String[] value = new String[2];
		int loop_c = 0;
		System.out.println("\nCustomize the hero as you like!\n");
		try {
			for (; loop_c < 2; loop_c++) {
				System.out.print(input_msg[loop_c]);
				value[loop_c] = myObj.nextLine();
				if (value[loop_c].equals("exit")) {
					this.setView();
					return ;
				}
				else if ((loop_c == 0 && controller.getGameModel().heroExists(value[loop_c]) == true) ||
							(loop_c != 0 && controller.getGameModel().isValidClass(value[loop_c]) == false))
				{
					loop_c--;
					continue ;
				}
				System.out.println("");
			}
			Hero new_hero = controller.getGameModel().generateHero(value[0], value[1]);
			if (controller.getGameModel().validateHero(new_hero, null) == true) {
				controller.addHero(new_hero);
				System.out.println("Hero has been succesfully created!");
			}
			if (controller.getStatus() == GameStatus.IN_HERO_MENU)
				loadHeroMenu();
			else
				controller.startGame(new_hero);
		} catch (Exception e) {
			controller.cleanup(1);
		}
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
			this.loadHeroMenu(); /*depends if game or not game */
		} catch (Exception e) {
			System.out.println("Error: Something went wrong listing the heroes\n" + e.getMessage());
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

	private void handleInput(String userInput) {
		try {
			if (userInput.equals("set view_mode=gui"))
				this.changeView();
			else {
				int value = Character.getNumericValue(userInput.charAt(0));
				if (userInput.length() != 1 || (value < 1 || value > 4)) {
					if (userInput.equals("set view_mode=console"))
						System.out.println("\nYou already are in console mode!");
					throw new Exception();
				}
				else
					ConsoleUtils.setOptions(value, this);
			}
		} catch (Exception e) {
			System.out.println("Error: Input must be either 1, 2, 3, 4 or \"set view_mode=gui\"\n");
			this.setView();
		}
	}

	public void loadGame() {
		try {
			controller.setStatus(GameStatus.IN_GAME);
			Game game = controller.getGame();
			System.out.println("Move the hero using the keyboard keys: w (up), s(down), d(right), a(left)");
			System.out.print("You can change to GUI mode in game using the \"set view_mode=gui\" command");
			System.out.println("You can leave the game using the \"exit\" command");
			while (true) {
				System.out.print("Introduce \"ok\" if you've understood your options: ");
				if (myObj.nextLine().equals("ok")) {
					System.out.print("\n");
					break ;
				}
			}
			showMap(game);
			runGame(game);
		} catch (Exception e) {
			controller.cleanup(1);
		}		
	}

	public void showMap(Game game) {
		int map_size = game.getMapSize();
		GameMap pos = game.getCurrentPosition();
		for (int y = 0; y < map_size; y++) {
			for (int x = 0; x < map_size; x++) {
				if (pos.matchCoords(x, y) == true)
					System.out.print("x");
				else
					System.out.print('.');
			}
			System.out.print("\n");
		}
		System.out.println("\nCurrent Position: (" + pos.getY() + "," + pos.getX() + ") marked in the map by the 'x'\n");
	}

	public void runGame(Game game) {
		try {
			String userInput;
			final String moves = "wsda";
			int index = 0;
			while (true) {
				System.out.print("In which direction would you like to move: 'w', 's', 'd', 'a'? ");
				userInput = myObj.nextLine();
				if (userInput.equals("set view_mode=gui"))
					this.changeView();
				else {
					index = moves.indexOf(userInput.charAt(0));
					if (userInput.length() != 1 || index == -1) {
						if (userInput.equals("set view_mode=console"))
							System.out.println("\nYou already are in console mode!");
						else if (userInput.equals("exit"))
							game.endGame(false);
						throw new Exception();
					}
					else {
						game.getCurrentPosition().updateCoords(index);
						showMap(game);
						game.playTurn();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error: Input must be either 1, 2, 3, 4 or \"set view_mode=gui\"\n");
			this.setView();
		}

	}

	public void showExitFromGame(Game game) {
		try {
			System.out.println("If you leave the progress done will be lost.");
			String userInput;
			while (true) {
				System.out.print("Are you sure you want to exit the game (y/n)? ");
				userInput = myObj.nextLine();
				if (userInput.equals("y"))
					this.loadGame();
				else {
					this.showMap(game);
					this.runGame(game);
				}
			}
		} catch (Exception e) {
			controller.cleanup(1);
		}
	}

	public void showVictory() {
		System.out.println("Congratulations! You've successfully won the game!");
		this.loadMenu();
	}

	public void showDefeat() {
		System.out.println("You've lost the game! We hope to see you soon!");
		this.loadMenu();
	}

	public void displayBattleResult(Hero hero, Villain villain) {
		System.out.println("\nYou've fallen in a villain trap!!\n");
		if (hero.getHP() > 0)
			System.out.println(hero.getName() + " has beaten the " + villain.getVillainType());
		else
			System.out.println(hero.getName() + " has been beaten by " + villain.getVillainType());
	}
}

/*

	- Menu
		- this.loadGame() in handleMenuInput()
	- Hero Creation and Listing
		- Handle input when insterting new hero values
		- 
 */