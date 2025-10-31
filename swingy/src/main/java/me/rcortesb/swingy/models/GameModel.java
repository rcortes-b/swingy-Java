package me.rcortesb.swingy.models;
import me.rcortesb.swingy.db_backend.DBHandler;
import me.rcortesb.swingy.controller.*;
import java.util.List;
import java.util.ArrayList;


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