package me.rcortesb.swingy.models.villains;

public class Orc extends Villain {

	public Orc(int hero_level) {
		this.attack = 15 + ((hero_level - 1) * 3);
		this.defense = 7 + ((hero_level - 1) * 2);
		this.hp = 55 + ((hero_level - 1) * 8);
	}

	public String getVillainType() {
		return "Orc";
	}
}