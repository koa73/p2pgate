package com.p2pgate.lib.rest.validation.constraint;


import com.p2pgate.lib.rest.validation.CVV;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Олег on 15.10.2016.
 */
public class CvvConstraint implements ConstraintValidator<CVV, String> {


    @Override
    public void initialize(CVV constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value == null) {
            return false;
        }
        return value.matches("^[1-9]\\d{2}|\\d[1-9]\\d|\\d{2}[1-9]$");
    }
}
