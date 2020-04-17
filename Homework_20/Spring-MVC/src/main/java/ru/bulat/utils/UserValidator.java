package ru.bulat.utils;

import ru.bulat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.bulat.services.UsersService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    private final UsersService usersService;

    @Autowired
    public UserValidator(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        Pattern patternPhone = Pattern.compile("\\+\\d(-\\d{3}){2}-\\d{4}");
        Matcher matcherPhone = patternPhone.matcher(user.getTelephone_number());
        Pattern patternDate = Pattern.compile("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])");
        Matcher matcherDate = patternDate.matcher(user.getDateOfBirth());
        if (usersService.find(user.getEmail()) != null) {
            errors.rejectValue(
                    "email", "This email is already in use", "This email is already in use"
            );
        }
        if (!matcherPhone.matches()) {
            errors.rejectValue(
                    "telephone_number", "Phone entered incorrectly", "Phone entered incorrectly"
            );
        }
        if (!matcherDate.matches()) {
            errors.rejectValue(
                    "dateOfBirth", "Date entered is incorrect", "Date entered is incorrect"
            );
        }
        if (!user.getPassword().trim().equals(user.getPasswordRepeat().trim())) {
            errors.rejectValue(
                    "passwordRepeat", "The password fields must match", "The password fields must match"
            );
        }
        if (!user.isCheck()) {
            errors.rejectValue(
                    "check", "Check the box", "Check the box"
            );
        }
    }
}
