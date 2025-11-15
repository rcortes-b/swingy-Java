package me.rcortesb.swingy.models.artifacts;

public class Weapon extends Artifact {
	public Weapon() {
		super();
		this.setStats();
		this.hp = 0;
	}

	public void setStats() {
		if (this.rarity.equals("Common")) {
			this.attack = 3;
			this.defense = 1;
			this.description = "A reliable forged blade carried by seasoned adventurers. " +
				"Its edge is not perfect, but its weight and balance make it deadly in capable hands";
		} else {
			this.attack = 4;
			this.defense = 2;
			this.description = "A sword crafted from a hardened dragon tooth, infused with ancient heat. " +
				"Its cutting power is unmatched for its size, and its enchanted spine reinforces the wielder's stance in battle";
		}
	}

	public String getType() {
		return "Weapon";
	}
}