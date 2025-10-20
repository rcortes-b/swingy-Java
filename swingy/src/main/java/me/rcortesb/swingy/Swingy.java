package me.rcortesb.swingy;


public class Swingy {
    public static void main(String[] args) {
		String mode = handle_input(args);
		System.out.println("You have choosen mode: " + mode);
    }

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
}