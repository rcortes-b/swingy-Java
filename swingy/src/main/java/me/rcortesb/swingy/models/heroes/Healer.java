package me.rcortesb.swingy.models.heroes;

public class Healer extends Hero {
	private static int BASE_ATTACK = 10;
	private static int ATTACK_INCREMENT = 2;
	private static int BASE_DEFENSE = 7;
	private static int DEFENSE_INCREMENT = 2;
	private static int BASE_HP = 55;
	private static int HP_INCREMENT = 9;
	
	public Healer(String name) {
		super(name, 1, 0, BASE_ATTACK, BASE_DEFENSE, BASE_HP);
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
		this.attack += ATTACK_INCREMENT;
		this.defense += DEFENSE_INCREMENT;
		this.hp += HP_INCREMENT;
	}

	public void setToDefault() {
		this.level = 1;
		this.experience = 0;
		this.attack = BASE_ATTACK;
		this.defense = BASE_DEFENSE;
		this.hp = BASE_HP;
	}

	public int getBaseAttack() { return BASE_ATTACK; }
	public int getBaseDefense() { return BASE_DEFENSE; }
	public int getBaseHP() { return BASE_HP; }
	public int getAttackIncrement() { return ATTACK_INCREMENT; }
	public int getDefenseIncrement() { return DEFENSE_INCREMENT; }
	public int getHPIncrement() { return HP_INCREMENT; }
}