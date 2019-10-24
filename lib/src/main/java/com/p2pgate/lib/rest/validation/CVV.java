package com.p2pgate.lib.rest.validation;


import com.p2pgate.lib.rest.validation.constraint.CvvConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CvvConstraint.class)
@Target( { ElementType.METHOD, ElementType.FIELD , ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CVV {
    String message() default "CVV";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}