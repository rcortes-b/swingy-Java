package me.rcortesb.swingy.models.villains;

public class Troll extends Villain {
	private final static int EXP = 800;
	
	public Troll(int hero_level) {
		this.attack = 10 + (hero_level - 1);
		this.defense = 5 + ((hero_level - 1) * 2);
		this.hp = 75 + ((hero_level - 1) * 12);
	}

	public String getVillainType() {
		return "Troll";
	}

	public int giveExperience() {
		return EXP;
	}
}