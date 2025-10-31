package me.rcortesb.swingy.models;

public class Hero {
	private String		name;
	private String		classType;
	private int			level;
	private int			experience;
	private int			attackDmg;
	private int			defenseDmg;
	private int			hp;
	private Artifact	artifact;

	public Hero(String p_name, String p_classType, int p_level, int p_exp, int p_attackDmg,
		int p_defenseDmg, int p_hp) {
		this.name = p_name;
		this.classType = p_classType;
		this.level = p_level;
		this.experience = p_exp;
		this.attackDmg = p_attackDmg;
		this.defenseDmg = p_defenseDmg;
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

	public int getAttack() {
		return attackDmg;
	}

	public int getDefense() {
		return defenseDmg;
	}

	public int getHP() {
		return hp;
	}
}