package me.rcortesb.swingy.validations;
import me.rcortesb.swingy.models.heroes.Hero;
import jakarta.validation.*;


/* THIS FILE IS EXCLUDED FROM THE MAVEN POM.XML FILE */

public class DefenseValidator implements ConstraintValidator<ValidDefense, Hero> {

    @Override
    public boolean isValid(Hero object, ConstraintValidatorContext context) {
        if (object == null)
			return true;
		int expected = object.getBaseDefense() + (object.getDefenseIncrement() * (object.getLevel() - 1));
		return (object.getDefense() == expected);
    }
}
