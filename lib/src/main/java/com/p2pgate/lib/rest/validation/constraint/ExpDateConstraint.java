package com.p2pgate.lib.rest.validation.constraint;

import com.p2pgate.lib.rest.validation.ExpDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

/**
 *
 * Created by Олег on 15.10.2016.
 */
public class ExpDateConstraint implements ConstraintValidator<ExpDate, String> {


    @Override
    public void initialize(ExpDate constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }

        if (value.matches("^(0[1-9]|1[012])\\d{2}$")){

            final int MAX_TO_FEATURE = 10;
            final int year = Integer.parseInt(value.replaceAll("^\\d{2}(\\d{2}$)","$1")) + 2000;
            final int month = Integer.parseInt(value.replaceAll("^(\\d{2})\\d{2}$","$1"));

            final Calendar cal = Calendar.getInstance();
            final int mMonth=cal.get(Calendar.MONTH);
            final int mYear = cal.get(Calendar.YEAR);

            return ((month >= mMonth+1 && year == mYear) || (year > mYear && year <= mYear + MAX_TO_FEATURE));

        }
        return false;
    }
}
