package com.p2pgate.lib.rest.validation.constraint;


import com.p2pgate.lib.rest.validation.Sum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * Created by Олег on 15.10.2016.
 */
public class SumConstraint implements ConstraintValidator<Sum, Float> {

    private int min;
    private int max;

    @Override
    public void initialize(Sum constraintAnnotation) {

        min = constraintAnnotation.min();
        max = constraintAnnotation.max();

    }

    @Override
    public boolean isValid(Float value, ConstraintValidatorContext context) {

        try{

            if (value == null){
                return true;
            }

            if (value < min || value > max){
                return false;
            }
            return true;

        } catch (Exception e){

            return false;
        }
    }
}
