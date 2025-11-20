package me.rcortesb.swingy.validations;
import me.rcortesb.swingy.models.heroes.*;
import jakarta.validation.*;


/* THIS FILE IS EXCLUDED FROM THE MAVEN POM.XML FILE */

public class ExpValidator implements ConstraintValidator<ValidExp, Hero> {

    @Override
    public boolean isValid(Hero object, ConstraintValidatorContext context) {
        if (object == null)
			return true;
		int level = object.getLevel();
		int required = level * 1000 + ((level - 1) * (level - 1)) * 450;
		int low_limit = 0;
		if (level > 1) {
			level--;
			low_limit = level * 1000 + ((level - 1) * (level - 1)) * 450;
		}
		System.out.println("low: " + low_limit);
		System.out.println("high: " + required);
		return (object.getExperience() >= low_limit && object.getExperience() < required);
    }
}
