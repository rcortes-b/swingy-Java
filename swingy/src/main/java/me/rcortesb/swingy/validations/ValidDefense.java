package me.rcortesb.swingy.validations;
import jakarta.validation.*;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


/* THIS FILE IS EXCLUDED FROM THE MAVEN POM.XML FILE */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DefenseValidator.class)
public @interface ValidDefense {
    String message() default "The defense of this hero has been modified";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
