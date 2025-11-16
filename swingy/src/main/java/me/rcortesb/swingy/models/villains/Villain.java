package me.rcortesb.swingy.models.villains;

public abstract class Villain {
	protected int	attack;
	protected int	defense;
	protected int	hp;

	public abstract String	getVillainType();
	public abstract int		giveExperience();
	protected Villain() {}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getHP() {
		return hp;
	}

	public void takeDamage(int dmg) {
		this.hp -= dmg;
	}
}