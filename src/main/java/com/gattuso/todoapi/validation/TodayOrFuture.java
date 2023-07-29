package com.gattuso.todoapi.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Constraint(validatedBy = TodayOrFutureValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface TodayOrFuture {
    String message() default "The date must be in the future(including today)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
