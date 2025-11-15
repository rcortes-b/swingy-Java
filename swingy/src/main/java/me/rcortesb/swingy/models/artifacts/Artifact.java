package me.rcortesb.swingy.models.artifacts;

public abstract class Artifact {
	protected int		attack;
	protected int		defense;
	protected int		hp;
	protected String	rarity;
	protected String	description;

	protected Artifact() {
		int random = (int)(Math.random() * 10);
		if (random < 3)
			this.rarity = "Uncommon";
		else
			this.rarity = "Common";
	}

	public String hasBeenDropped() {
		return ("A " + this.getType() + " of rarity " + this.rarity + "has been dropped!");
	}

	public String getDescription() {
		return this.description;
	}

	public abstract String getType();
}