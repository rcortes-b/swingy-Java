package me.rcortesb.swingy.models.heroes;
import me.rcortesb.swingy.models.artifacts.Artifact;
import me.rcortesb.swingy.controller.Controller;
import me.rcortesb.swingy.validations.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;

/*	| Hero    | HP | ATK | DEF | HP/Lvl | ATK/Lvl | DEF/Lvl |
	| ------- | -- | --- | --- | ------ | ------- | ------- |
	| Warrior | 60 | 14  | 8   | +10    | +3      | +2      |
	| Wizard  | 45 | 18  | 4   | +8     | +4      | +1      |
	| Healer  | 55 | 10  | 7   | +9     | +2      | +2      |
 */

/* A childHero can be:
					- Default Hero (no param constructor)
					- New hero (name)
					- Existing hero loaded from DB (name, level, exp, atk, def, hp)
*/
@ValidAttack
@ValidDefense
@ValidExp
public abstract class Hero {
	@NotEmpty(message = "Name not specified")
	@Size(min=1, max=15, message= "Name must be between 1 to 15 characters")
	protected String	name;
	
	@Min(value=1, message="Minimum level is 1")
	@Max(value=10, message="Maximum level is 10")
	protected int		level;
	
	protected int		experience;
	protected int		attack;
	protected int		defense;

	@Min(value = 1, message="Hero cannot be loaded because it is deceased")
	protected int		hp;
	protected Artifact	artifact;

	public abstract String getClassType();
	public abstract void incrementLevel();
	public abstract void setToDefault();
	public abstract int getBaseAttack();
	public abstract int getBaseDefense();
	public abstract int getBaseHP();
	public abstract int getAttackIncrement();
	public abstract int getDefenseIncrement();
	public abstract int getHPIncrement();

	protected Hero(String p_name, int p_level, int p_exp,
					int p_attack, int p_defense, int p_hp)
	{
		this.name = p_name;
		this.level = p_level;
		this.experience = p_exp;
		this.attack = p_attack;
		this.defense = p_defense;
		this.hp = p_hp;
		this.artifact = null;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public int getExperience() {
		return experience;
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

	public void attachArtifact(Artifact item) {
		Controller.getViewModel().showArtifactDropped(this.artifact, item);
		if (item.isAttachable() == true) {
			this.artifact = item;
			this.hp += item.getHP();
		}
	}

	public void dettachArtifact() {
		this.artifact = null;
	}

	public int getDamage() {
		if (this.artifact != null) {
			return (attack + artifact.getAttack());
		}
		return attack;
	}

	public int getResistance() {
		if (this.artifact != null) {
			return (defense + artifact.getDefense());
		}
		return defense;
	}

	public void takeDamage(int dmg) {
		this.hp -= dmg;
	}

	public boolean isAlive() {
		if (this.hp > 0)
			return true;
		return false;
	}

	public void gainExperience(int amount) {
		if (this.level == 10)
			return ;

		int exp_required = this.level * 1000 + ((this.level - 1) * (this.level - 1)) * 450;
		this.experience += amount;
		if (this.experience >= exp_required) {
			if (this.level == 9)
				this.experience = exp_required;
			this.incrementLevel();
			Controller.getViewModel().showLevelUp(this);
		}
	}

}