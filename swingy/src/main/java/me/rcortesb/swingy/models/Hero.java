package me.rcortesb.swingy.models;

public class Hero {
	private String		name;
	private String		classType;
	private int			level;
	private int			experience;
	private int			attack;
	private int			defense;
	private int			hp;
	private Artifact	artifact;

	public Hero(String p_name, String p_classType, int p_level, int p_exp, int p_attack,
		int p_defense, int p_hp) {
		this.name = p_name;
		this.classType = p_classType;
		this.level = p_level;
		this.experience = p_exp;
		this.attack = p_attack;
		this.defense = p_defense;
		this.hp = p_hp;
		this.artifact = null;
	}

	public String getName() {
		return name;
	}

	public String getClassType() {
		return classType;
	}

	public int getLevel() {
		return level;
	}

	public int getExperience() {
		return experience;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getHP() {
		return hp;
	}
}