package com.p2pgate.lib.rest.validation.constraint;

import com.p2pgate.lib.rest.validation.PAN;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Олег on 15.10.2016.
 */
public class PanConstraint implements ConstraintValidator<PAN, String> {

    @Override
    public void initialize(PAN constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }

        //return value.matches("^(4[0-9]{2,12}(?:[0-9]{3})?)|(5[1-5][0-9]{1,14})$");
        return value.matches("^(4[0-9]{2,12}(?:[0-9]{3})?)|(5[1-5][0-9]{1,14}|(220[0-4][0-9]{12}))|(^(5018|5020|5038|5612|5893|6304|6759|6761|6762|6763|0604|6390)\\d+$)$");
    }
}
