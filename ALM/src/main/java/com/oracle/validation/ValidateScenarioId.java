package com.oracle.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ScenarioIdValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidateScenarioId {

    String message() default "Scenario Already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}