package me.rcortesb.swingy.models;

public class Villain {
	private String		name;
	private String		classType;
	private int			attackDmg;
	private int			defenseDmg;
	private int			hp;

	public Villain(String p_name, String p_classType, int p_attackDmg, int p_defenseDmg, int p_hp) {
		this.name = p_name;
		this.classType = p_classType;
		this.attackDmg = p_attackDmg;
		this.defenseDmg = p_defenseDmg;
		this.hp = p_hp;
	}

	public String getName() {
		return name;
	}
}