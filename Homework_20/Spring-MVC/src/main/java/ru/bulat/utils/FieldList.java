package ru.bulat.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@Constraint(validatedBy = FieldListValidator.class)
public @interface FieldList {
    Class<?>[] groups() default {};

    String message() default "{Object has empty collections}";

    Class<? extends Payload>[] payload() default {};
}