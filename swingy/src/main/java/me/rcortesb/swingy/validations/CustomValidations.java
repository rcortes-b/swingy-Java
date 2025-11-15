package me.rcortesb.swingy.validations;
import jakarta.validation.*;

/* THIS FILE IS EXCLUDED FROM THE MAVEN POM.XML FILE */

public class HpLevelValidator implements ConstraintValidator<ValidHp, CharacterStats> {

    @Override
    public boolean isValid(CharacterStats stats, ConstraintValidatorContext context) {
        if (stats == null) return true; // do not validate null object

        return stats.getHp() >= 15 * stats.getLevel();
    }
}
