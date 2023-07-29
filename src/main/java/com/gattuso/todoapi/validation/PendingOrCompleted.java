package com.gattuso.todoapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = PendingOrCompletedValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PendingOrCompleted {
    String message() default "The field must contain 'pending' or 'completed' status";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
