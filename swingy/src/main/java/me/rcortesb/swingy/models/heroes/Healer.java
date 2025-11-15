package me.rcortesb.swingy.models.heroes;

public class Healer extends Hero {

	public Healer() {
		super("Gentle Healer", 1, 0, 10, 7, 55);
	}

	public Healer(String name) {
		super(name, 1, 0, 10, 7, 55);
	}

	public Healer(String p_name, int p_level, int p_exp, int p_attack,
					int p_defense, int p_hp) {
		super(p_name, p_level, p_exp, p_attack, p_defense, p_hp);
	}

	public String getClassType() {
		return "Healer";
	}

	public void incrementLevel() {
		this.level++;
		this.attack += 2;
		this.defense += 2;
		this.hp += 9;
	}

}