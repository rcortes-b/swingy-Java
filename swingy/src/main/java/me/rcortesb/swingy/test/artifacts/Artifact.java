
import java.lang.Math;

public abstract class Artifact {
	//validation of artifactLevel min=1 max=5
	private int artifactLevel;
	private int increaseStat[] = {3, 5, 7, 9, 12};
	private String artifactType;
	public abstract Artifact(int villainLevel) {
		this.artifactLevel = getArtifactLevel(villainLevel);
		this.artifactType = getArtifactType((int)(Math.random() % 3))
	}

	//In the other Weapon, etc classes i've to return "weapon" in getArtifactType()
	public abstract String getArtifactType();
	/*
		increaseStat(Hero hero) in Weapon == hero.setAttack(hero.getAttack() + this.increaseStat[this.artifactLevel - 1])
	 */
	public abstract void increaseStat(Hero hero);
	/*
	Artifact level based on villain level (levels of heroes/villains goes from 1 to 10):
		Villain Lvl 1 --> Artifact Lvl 1
		Villain Lvl 2 --> Artifact Lvl 1
		Villain Lvl 3 --> Artifact Lvl 1
		Villain Lvl 4 --> Artifact Lvl 2
		Villain Lvl 5 --> Artifact Lvl 2
		Villain Lvl 6 --> Artifact Lvl 3
		Villain Lvl 7 --> Artifact Lvl 3
		Villain Lvl 8 --> Artifact Lvl 4
		Villain Lvl 9 --> Artifact Lvl 4
		Villain Lvl 10 --> Artifact Lvl 5
	*/
	private int getArtifactLevel(int villainLevel) {
		int level = villainLevel / 2;
		if (level == 0)
			level = 1;
		return level;
	} 
}

public class Weapon extends Artifact {
	super(villainLevel); //somwhow i've to reach this value. now its not valid i think
}

new Weapon(3);

/*if hero.wins
	artifactHasDropped()
		hero.setArtifact(ArtifactFactory.getArtifactFactory().createArtifact(villainLevel))
		***inside of hero.setArtifact(newArtifact):
			this.artifact = newArtifact;
			this.artifact.increaseStat(Hero hero)
			***inside of artifact.increaseStat(this)

