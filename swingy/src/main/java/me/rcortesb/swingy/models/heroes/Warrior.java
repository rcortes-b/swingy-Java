package me.rcortesb.swingy.models.heroes;

public class Warrior extends Hero {

	public Warrior() {
		super("Furious Warrior", 1, 0, 14, 8, 60);
	}

	public Warrior(String name) {
		super(name, 1, 0, 14, 8, 60);
	}

	public Warrior(String p_name, int p_level, int p_exp, int p_attack,
					int p_defense, int p_hp) {
		super(p_name, p_level, p_exp, p_attack, p_defense, p_hp);
	}

	public String getClassType() {
		return "Warrior";
	}

	public void incrementLevel() {
		this.attack += 3;
		this.defense += 2;
		this.hp += 10;
	}
}