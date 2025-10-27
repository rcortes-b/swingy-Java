package me.rcortesb.swingy.views;
import me.rcortesb.swingy.controller.*;
import java.util.Scanner;
public class Console_View implements ViewModel {
	private static Scanner myObj = new Scanner(System.in);

	public void launchConsole() {
		System.out.println("\n--------------------------------------------\n");
		System.out.println("Welcome to Achieve The Border!!");
		System.out.println("Currently you're in view_mode=console, type \"set view_mode=gui\" to change to a graphic interface");
		loadMenu();
	}
	public void	setView() {
		GameStatus status = Controller.getController().getStatus();
		switch (status) {
			case IN_MENU:
				this.loadMenu();
				break ;
			case IN_CREATE_HERO:
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
			System.out.println("\nWhat do you want to do?");
			System.out.println("\t1 - Start game");
			System.out.println("\t2 - Create a hero");
			System.out.println("\t3 - Change to GUI mode");
			System.out.println("\t4 - Exit\n");
			System.out.print("Introduce option: ");
			String userInput = myObj.nextLine();
			handleUserInput(userInput);
		} catch (Exception e) {
			exitFromGame(1);
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

	private void exitFromGame(int exitCode) {
		System.out.println("\n\nThanks for playing, we hope to see you soon!\n");
		System.exit(exitCode);
	}

	public void changeViewMode() {
		Controller.getController().loadGUI();
	}

	private void handleUserInput(String userInput) {
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
								System.out.println("Here goes the hero creation!");
								break;
						case 3:
								this.changeViewMode();
								break;
						case 4:
								exitFromGame(0);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error: Input must be either 1, 2, 3, 4 or \"set view_mode=gui\"\n");
			loadMenu();
		}
	}
}