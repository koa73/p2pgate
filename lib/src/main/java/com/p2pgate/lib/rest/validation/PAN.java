package com.p2pgate.lib.rest.validation;

import com.p2pgate.lib.rest.validation.constraint.PanConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PanConstraint.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PAN {
    String message() default ". It's not valid pan card number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}