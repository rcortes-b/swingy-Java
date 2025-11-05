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

	public boolean heroExists(Hero newHero) {
		String heroName = newHero.getName();
		for (Hero hero : heroes) {
			if (hero.getName().equals(heroName)) {
				System.out.println("Error: A hero with name " + heroName + " already exists.");
				return true;
			}
		}
		return false;
	}

	public boolean isHeroValid(Hero new_hero) {
		try {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
	
		Set<ConstraintViolation<Hero>> violations = validator.validate(new_hero);
		if (violations.size() == 0)
			return true;
		for (ConstraintViolation<Hero> violation : violations) {
            System.out.println("Wrong value at creation: " + violation.getMessage());
        }
		return false;
		} catch (Exception e) {
			System.out.println("Erorr: " + e.getMessage());
		}
		return false;
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