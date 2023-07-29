package com.gattuso.todoapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class TodayOrFutureValidator implements ConstraintValidator<TodayOrFuture, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate currentDate = LocalDate.now();
        return (localDate.isAfter(currentDate)|| localDate.isEqual(currentDate));
    }
}
