package me.rcortesb.swingy.views;
import me.rcortesb.swingy.controller.Controller;
import java.util.Scanner;
public class Console_View extends View {
	private Scanner myObj = new Scanner(System.in);

	public String getView() {
		return "console_view";
	}
	public void loadMenu2() {}
	public void launchApp() {
		System.out.println("\n--------------------------------------------\n");
		System.out.println("Welcome to Achieve The Border!!");
		System.out.println("Currently you're in view_mode=console, type \"set view_mode=gui\" to change to a graphic interface");
		this.loadMenu();
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
			this.exitFromGame(1);
		}
	}

	public void displayGameOptions() {
		System.out.println("\nGame Options:");
		System.out.println("\t- \"w\" -> Move 1 step to the North");
		System.out.println("\t- \"d\" -> Move 1 step to the East");
		System.out.println("\t- \"s\" -> Move 1 step to the South");
		System.out.println("\t- \"a\" -> Move 1 step to the West");
		System.out.println("\t- \"set view_mode=<gui,console>\" -> Change between user interfaces.");
		this.loadMenu();
	}

	private void exitFromGame(int exitCode) {
		System.out.println("\n\nThanks for playing, we hope to see you soon!\n");
		System.exit(exitCode);
	}

	public void deleteUI() {
		myObj.close();
	}

	private void handleUserInput(String userInput) {
		try {
			if (userInput.equals("set view_mode=gui"))
				Controller.applyView("gui", false);
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
								Controller.applyView("gui", false);
								break;
						case 4:
								this.exitFromGame(0);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error: Input must be either 1, 2, 3, 4 or \"set view_mode=gui\"\n");
			this.loadMenu();
		}
	}
}