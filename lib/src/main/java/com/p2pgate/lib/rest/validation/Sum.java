package com.p2pgate.lib.rest.validation;


import com.p2pgate.lib.rest.validation.constraint.SumConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Digits;
import java.lang.annotation.*;


@Documented
@Digits(integer=6, fraction=2)
@Constraint(validatedBy = SumConstraint.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)

public @interface Sum {

    String message() default "Amount should be between {min}<=>{max} or null";

    int min();
    int max();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target( { ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface  List {
        Sum[] value();
    }
}