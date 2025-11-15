package me.rcortesb.swingy.validations;
import jakarta.validation.*;

/* THIS FILE IS EXCLUDED FROM THE MAVEN POM.XML FILE */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HpLevelValidator.class)
public @interface ValidHp {
    String message() default "HP must be >= 15 * level";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
