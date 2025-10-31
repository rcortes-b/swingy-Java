package me.rcortesb.swingy.models;

public class Villain {
	private String		name;
	private String		classType;
	private int			attack;
	private int			defense;
	private int			hp;

	public Villain(String p_name, String p_classType, int p_attack, int p_defense, int p_hp) {
		this.name = p_name;
		this.classType = p_classType;
		this.attack = p_attack;
		this.defense = p_defense;
		this.hp = p_hp;
	}

	public String getName() {
		return name;
	}
}