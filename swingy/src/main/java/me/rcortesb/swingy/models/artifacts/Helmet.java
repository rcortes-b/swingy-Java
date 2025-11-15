package me.rcortesb.swingy.models.artifacts;

public class Helmet extends Artifact {
	public Helmet() {
		super();
		this.setStats();
		this.attack = 0;
	}

	public void setStats() {
		if (this.rarity.equals("Common")) {
			this.defense = 2;
			this.hp = 10;
			this.description = "An old iron helm with scratches from long-forgotten battles. " +
								"It offers basic protection and keeps the wearer steady when taking blows";
		} else {
			this.defense = 3;
			this.hp = 15;
			this.description = "A masterfully forged helmet worn by elite guardians. " +
								"Its reinforced plating absorbs impacts with ease, shielding the wearer from even brutal strikes";
		}
	}

	public String getType() {
		return "Helmet";
	}
}