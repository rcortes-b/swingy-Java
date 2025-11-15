package me.rcortesb.swingy.models.artifacts;

public class Lifebloom extends Artifact {
	public Lifebloom() {
		super();
		this.setStats();
		this.attack = 0;
	}

	public void setStats() {
		if (this.rarity.equals("Common")) {
			this.defense = 0;
			this.hp = 12;
			this.description = "A simple charm infused with gentle restorative energy. " +
							"It strengthens the wearer's vitality, helping them recover from minor wounds and endure longer in combat";
		} else {
			this.defense = 1;
			this.hp = 15;
			this.description = "A sacred emblem blessed by ancient spirits of growth. Its life-rich aura flows into the wearer,"
								+ " bolstering their endurance and fortifying their spirit against harm";
		}
	}

	public String getType() {
		return "Lifebloom";
	}
}