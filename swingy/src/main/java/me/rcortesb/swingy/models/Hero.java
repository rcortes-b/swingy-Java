package me.rcortesb.swingy.models;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class Hero {
	@NotEmpty(message = "Name not specified")
	@Size(min=1, max=15, message= "Name must be between 1 to 15 characters")
	private String		name;

	@Pattern(regexp = "Warrior|Wizard|Healer", message = "Class must be either Warrior, Wizard or Healer")
	private String		classType;

	@Min(value=1, message="Minimum level is 1")
	private int			level;

	@Min(value=0, message="Minumun amount of XP is 0")
	private int			experience;

	@Min(value = 7, message = "The minimun attack stat valid is 7")
    @Max(value = 15, message = "The maximum attack stat valid is 15")
	private int			attack;

	@Min(value = 5, message = "The minimun defense stat valid is 5")
    @Max(value = 10, message = "The maximum defense stat valid is 10")
	private int			defense;

	@Min(value = 15, message = "The minimun hp stat valid is 15")
    @Max(value = 25, message = "The maximum hp stat valid is 25")
	private int			hp;

	private Artifact	artifact;

	public Hero(String p_name, String p_classType, int p_level, int p_exp, int p_attack,
		int p_defense, int p_hp) {
		this.name = p_name;
		this.classType = p_classType;
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

	public String getClassType() {
		return classType;
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
}