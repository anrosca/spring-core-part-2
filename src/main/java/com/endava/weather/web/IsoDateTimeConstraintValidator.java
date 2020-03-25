package com.endava.weather.web;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class IsoDateTimeConstraintValidator implements ConstraintValidator<IsoDateTime, String> {
    private IsoDateTime constraintAnnotation;

    @Override
    public void initialize(IsoDateTime constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(constraintAnnotation.pattern()));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
