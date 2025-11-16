package me.rcortesb.swingy.validations;
import me.rcortesb.swingy.models.heroes.*;
import jakarta.validation.*;


/* THIS FILE IS EXCLUDED FROM THE MAVEN POM.XML FILE */

public class AttackValidator implements ConstraintValidator<ValidAttack, Hero> {

    @Override
    public boolean isValid(Hero object, ConstraintValidatorContext context) {
        if (object == null)
			return true;

		int expected = object.getBaseAttack() + (object.getAttackIncrement() * (object.getLevel() - 1));
		return (object.getAttack() == expected);
    }
}
