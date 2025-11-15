package me.rcortesb.swingy.models.heroes;

public class Wizard extends Hero {

	public Wizard(String name) {
		super(name, 1, 0, 18, 4, 45);
	}

	public Wizard(String p_name, int p_level, int p_exp, int p_attack,
					int p_defense, int p_hp) {
		super(p_name, p_level, p_exp, p_attack, p_defense, p_hp);
	}

	public String getClassType() {
		return "Wizard";
	}

	public void incrementLevel() {
		this.attack += 4;
		this.defense += 1;
		this.hp += 8;
	}
}