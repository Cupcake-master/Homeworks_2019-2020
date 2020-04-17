package ru.bulat.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.List;

public class FieldListValidator implements ConstraintValidator<FieldList, Object> {

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Field[] allFields = o.getClass().getDeclaredFields();
        for (Field field : allFields) {
            if (List.class.isAssignableFrom(field.getType())) {
                if (List.class.cast(field).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}
