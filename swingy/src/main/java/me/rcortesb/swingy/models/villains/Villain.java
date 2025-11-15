package me.rcortesb.swingy.models.villains;

public abstract class Villain {
	protected int	attack;
	protected int	defense;
	protected int	hp;

	public abstract String getVillainType();

	protected Villain() {}

	protected int getAttack() {
		return attack;
	}

	protected int getDefense() {
		return defense;
	}

	protected int getHP() {
		return hp;
	}
}