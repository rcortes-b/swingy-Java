package me.rcortesb.swingy;
import me.rcortesb.swingy.controller.Controller;
import me.rcortesb.swingy.views.Console_View;
import me.rcortesb.swingy.views.GUI_View;

public class Swingy {

	private static String handle_input(String[] args) {
		try {
			if (args.length != 1) {
				throw new Exception();
			}
			if (!args[0].equals("console") && !args[0].equals("gui"))
				throw new Exception();
		} catch (Exception e) {
			System.err.println("Error: You may only specify the game mode \"console or gui\"");
			System.exit(1);
		}
		return args[0];
	}

	public static void main(String[] args) {
		String mode = handle_input(args);
		Controller controller = Controller.getController();

		if (mode.equals("gui"))
			controller.applyView("gui", true);
		else
			controller.applyView("console", true);
    }
}