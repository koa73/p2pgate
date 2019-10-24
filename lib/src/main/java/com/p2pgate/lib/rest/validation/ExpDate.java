package com.p2pgate.lib.rest.validation;


import com.p2pgate.lib.rest.validation.constraint.ExpDateConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExpDateConstraint.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpDate {
    String message() default " expDate ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
