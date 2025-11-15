package me.rcortesb.swingy.models.villains;

/*| Villain | HP     | ATK | DEF | HP/Lvl | ATK/Lvl | DEF/Lvl | Purpose       | Encounter Chance |
| ------- | ------ | --- | --- | ------ | ------- | ------- | ------------- | ---------------- |
| Goblin  | **35** | 12  | 4   | +6     | +2      | +1      | Fast harasser | 45%              |
|    |
 */

public class Goblin extends Villain {

	public Goblin(int hero_level) {
		this.attack = 12 + ((hero_level - 1) * 2);
		this.defense = 4 + (hero_level - 1);
		this.hp = 35 + ((hero_level - 1) * 6);
	}

	public String getVillainType() {
		return "Goblin";
	}
}