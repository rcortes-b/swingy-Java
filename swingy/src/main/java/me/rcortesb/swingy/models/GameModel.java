package me.rcortesb.swingy.models;
import me.rcortesb.swingy.db_backend.DBHandler;
import me.rcortesb.swingy.controller.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;


public class GameModel {
	private static Controller controller;
	private List<Hero> heroes;
	private List<Villain> villains;
	private List<Artifact> artifacts;

	public GameModel() {
		this.controller = Controller.getController();
		this.heroes = new ArrayList<>();
		this.villains = new ArrayList<>();
		this.artifacts = new ArrayList<>();
		this.controller.getDBHandler().readOperation(this);
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

	public List<Villain> getVillains() {
		return villains;
	}

	public List<Artifact> getArtifacts() {
		return artifacts;
	}
}