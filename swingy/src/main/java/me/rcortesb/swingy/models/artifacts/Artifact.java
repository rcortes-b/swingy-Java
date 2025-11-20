package me.rcortesb.swingy.models.artifacts;

public abstract class Artifact {
	protected int		attack;
	protected int		defense;
	protected int		hp;
	protected String	rarity;
	protected String	description;
	protected boolean	isAttachable;

	public abstract String getType();

	protected Artifact() {
		this.isAttachable = true;
		int random = (int)(Math.random() * 10);
		if (random < 3)
			this.rarity = "Uncommon";
		else
			this.rarity = "Common";
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

	public String hasBeenDropped() {
		return ("A " + this.getType() + " of rarity " + this.rarity + " has been dropped!");
	}

	public String hasBeenAttached() {
		return ("A " + this.getType() + " of rarity " + this.rarity + " has been attached!");
	}

	public String getDescription() {
		return this.description;
	}

	public String getRarity() {
		return rarity;
	}

	public boolean isAttachable() {
		return isAttachable;
	}

	public void setAsNotAttachable() {
		isAttachable = false;
	}
}