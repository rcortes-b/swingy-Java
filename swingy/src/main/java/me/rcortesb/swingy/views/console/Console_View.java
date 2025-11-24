package me.rcortesb.swingy.views.console;
import me.rcortesb.swingy.views.ViewModel;
import me.rcortesb.swingy.controller.*;
import me.rcortesb.swingy.models.game.Game;
import me.rcortesb.swingy.models.game.GameMap;
import me.rcortesb.swingy.models.heroes.Hero;
import me.rcortesb.swingy.models.villains.Villain;
import me.rcortesb.swingy.models.artifacts.Artifact;
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
			controller.handleError("Error: EOF found.", true);
		}
	}

	public void loadHeroMenu() {
		try {
			controller.setStatus(GameStatus.IN_HERO_MENU);
			System.out.println("\nWelcome to the hero factory!\n");
			System.out.println("\t1 - Create a new hero");
			System.out.println("\t2 - List your heroes");
			System.out.println("\t3 - Change to GUI mode");
			System.out.println("\t4 - Go back to menu\n");
			System.out.print("Introduce option: ");
			String userInput = myObj.nextLine();
			handleInput(userInput);
		} catch (Exception e) {
			controller.handleError("Error: EOF found.", true);
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
			controller.handleError("Error: EOF found.", true);
		}
	}

	public void createHero() {
		String heroName, heroClass;
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
			controller.handleError("Error: EOF found.", true);
		}
	}

	public void listHeroes() {
		try {
			List<Hero> heroes = controller.getGameModel().getHeroes();
			int[]		table_size = {15, 7, 3, 3, 3, 3};
			System.out.println("\n | name            | class   | lvl | atk | def | hp |");
			for (Hero hero : heroes) {
				ConsoleUtils.printValue(null, 6, 53);
				for (int i = 0; i < 6; i++) {
					ConsoleUtils.printValue(hero, i, table_size[i]);
				}
				System.out.println("|");
			}
			ConsoleUtils.printValue(null, 6, 53);
			if (controller.getStatus() == GameStatus.IN_GAME_MENU) {
				Hero selected_hero = null;
				while (true) {
					System.out.print("\nIntroduce the name of the hero you would like to play with: ");
					String userInput = myObj.nextLine();
					selected_hero = ConsoleUtils.handleHeroSelection(heroes, userInput);
					if (selected_hero != null) {
						controller.startGame(selected_hero);
						break ;
					}
				} 
			}
			else {
				loadHeroMenu();
			}
		} catch (Exception e) {
			controller.handleError("Error: EOF found.", true);
		}
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
			System.out.println("\nTHESE ARE YOUR GAME OPTIONS:\n");
			System.out.println("Move the hero using the keyboard keys: w (up), s(down), d(right), a(left)");
			System.out.print("You can change to GUI mode in game using the \"set view_mode=gui\" command\n");
			System.out.println("You can leave the game using the \"exit\" command\n");
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
			controller.handleError("Error: EOF found.", true);
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
				if (userInput.equals("set view_mode=gui")) {
					this.changeView();
					break ;
				}
				else {
					index = moves.indexOf(userInput.charAt(0));
					if (userInput.length() != 1 || index == -1) {
						if (userInput.equals("set view_mode=console"))
							System.out.println("\nYou already are in console mode!");
						else if (userInput.equals("exit")) {
							game.endGame(false);
							break ;
						}
					}
					else {
						game.getCurrentPosition().updateCoords(index);
						showMap(game);
						game.playTurn();
					}
				}
			}
		} catch (Exception e) {
			controller.handleError("Error: EOF found.", true);
		}

	}

	public void showExitFromGame(Game game) {
		try {
			System.out.println("If you leave now, the progress done will be lost.");
			String userInput;
			while (true) {
				System.out.print("Are you sure you want to exit the game (y/n)? ");
				userInput = myObj.nextLine();
				if (userInput.equals("y")) {
					Controller.removeGame();
					this.loadMenu();
					break ;
				}
				else if (userInput.equals("n")){
					this.showMap(game);
					this.runGame(game);
					break ;
				}
			}
		} catch (Exception e) {
			controller.handleError("Error: EOF found.", true);
		}
	}

	public void showVictory() {
		System.out.println("Congratulations! You've successfully won the game!");
		Controller.removeGame();
		this.loadMenu();
	}

	public void showDefeat() {
		System.out.println("You've lost the game! We hope to see you soon!");
		Controller.removeGame();
		this.loadMenu();
	}

	public void showError(String msg) {
		System.err.println(msg);
	}

	public void showLevelUp(Hero hero) {
		System.out.println(hero.getName() + " is now level " + hero.getLevel() + "!");
	}

	public void showArtifactDropped(Artifact curr_item, Artifact new_item) {
		System.out.println(new_item.hasBeenDropped());
		try {
			if (curr_item == null)
				showArtifactAttached(new_item);
			else {
				while (true) {
					System.out.print("Do you want to change your " + curr_item.getType() + " of rarity " + curr_item.getRarity() + "? (y/n) ");
					String userInput = myObj.nextLine();
					if (userInput.equals("y")) {
						showArtifactAttached(new_item);
						break ;
					}
					else if (userInput.equals("n")) {
						new_item.setAsNotAttachable();
						break ;
					}
				}
			}
		} catch (Exception e) {
			controller.handleError("Error: EOF found.", true);
		}
	}

	public void showArtifactAttached(Artifact item) {
		System.out.println(item.hasBeenAttached());
	}

	public void displayBattleResult(Hero hero, Villain villain) {
		System.out.println("\nYou've fallen in a villain trap!!\n");
		if (hero.getHP() > 0)
			System.out.println(hero.getName() + " has beaten the " + villain.getVillainType());
		else
			System.out.println(hero.getName() + " has been beaten by " + villain.getVillainType());
	}
}