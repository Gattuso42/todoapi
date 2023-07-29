package com.gattuso.todoapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PendingOrCompletedValidator implements ConstraintValidator<PendingOrCompleted,String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.equals("pending") || s.equals("completed");
    }
}
