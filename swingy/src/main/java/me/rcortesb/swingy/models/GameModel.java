package me.rcortesb.swingy.models;
import me.rcortesb.swingy.db_backend.DBHandler;
import me.rcortesb.swingy.controller.*;
import me.rcortesb.swingy.models.heroes.*;
import me.rcortesb.swingy.models.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

/* GameModel is the one who will generate a random artifact + a random villain */

public class GameModel {
	private static Controller controller;
	private List<Hero> heroes;
	final private String[] defaultHeroes = {"Furious Warrior", "Magic Wizard", "Gentle Healer"};
	final private String[] defaultClasses = {"Warrior", "Wizard", "Healer"};

	public GameModel() {
		this.controller = Controller.getController();
		this.initializeHeroes();
		this.controller.getDBHandler().readOperation(this);
	}

	/* Copy constructor */

	public GameModel(GameModel model) {
		this.controller = model.controller;
		this.heroes = model.heroes;
	}

	private void initializeHeroes() {
		this.heroes = new ArrayList<>();
		this.heroes.add(new Warrior(defaultHeroes[0]));
		this.heroes.add(new Wizard(defaultHeroes[1]));
		this.heroes.add(new Healer(defaultHeroes[2]));
	}

	public Hero generateHero(String name, String classType) {
		if (classType.equals(defaultClasses[0]))
			return (new Warrior(name));
		else if (classType.equals(defaultClasses[1]))
			return (new Wizard(name));
		return (new Healer(name));
	}

	/* utils */

	public boolean heroExists(String heroName) {
		for (Hero hero : heroes) {
			if (hero.getName().equals(heroName)) {
				if (controller.isConsoleMode() == true)
					System.out.println("Error: A hero with name " + heroName + " already exists");
				return true;
			}
		}
		return false;
	}

	public boolean isDefaultName(String name) {
		for (int i = 0; i < 3; i++) {
			if (name.equals(defaultHeroes[i]))
				return true;
		}
		return false;
	}

	public boolean isValidClass(String className) {
		for (int i = 0; i < 3; i++) {
			if (className.equals(defaultClasses[i]))
				return true;
		}
		return false;
	}

	public boolean isHeroValid(Hero new_hero, List<String> error_log) {
		try {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
	
		Set<ConstraintViolation<Hero>> violations = validator.validate(new_hero);
		if (violations.size() == 0)
			return true;
		for (ConstraintViolation<Hero> violation : violations) {
			if (error_log != null)
				error_log.add("Wrong value at creation: " + violation.getMessage());
			else
          		System.out.println("Wrong value at creation: " + violation.getMessage());
        }
		return false;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return false;
	}


	/* 
		First loop is only for console mode, to validate if the class chosen is correct 
		Second loop is for both, to validate if the value in Attacd-Defense-HP is numeric
	*/
	public boolean validateHeroInput(String value, int loop) {
		if (loop == 1) {
			if (!value.equals("Warrior") && !value.equals("Wizard") && !value.equals("Healer")) {
				System.out.println("Bad input: Class must be either Warrior, Wizard or Healer");
				return false;
			}
		}
		else if (loop >= 2 && loop <= 4) {
			for (int i = 0; i < value.length(); i++) {
				if (!Character.isDigit(value.charAt(i))) {
					if (controller.isConsoleMode() == true)
						System.out.println("Bad input: Value must be only numeric");
					return false;
				}
			}
		}
		return true;
	}

	/* Getters */

	public List<Hero> getHeroes() {
		return heroes;
	}
}