package com.bredex.test.services.validators;

import com.bredex.test.web.dtos.RegistrationDto;
import com.bredex.test.web.errors.CustomExceptionModel;
import com.bredex.test.web.errors.FieldValidationException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class RegistrationDtoValidator {

    public List<CustomExceptionModel> validate(RegistrationDto registrationDto) {
        List<CustomExceptionModel> errors = new LinkedList<>();

        this.checkUserNameErrors(registrationDto.getUserName(), errors);

        this.checkEmailErrors(registrationDto.getEmail(), errors);

        this.checkPasswordErrors(registrationDto.getPassword(), errors);

        return errors;
    }

    private void checkPasswordErrors(String password, List<CustomExceptionModel> errors) {
        if (password == null) {
            errors.add(new FieldValidationException().ofWithField("RegistrationDto", "Password should not empty!", "password"));
            return;
        }

        if (password.length() < 8) {
            errors.add(new FieldValidationException().ofWithField("RegistrationDto", "Password length should be minimum 8 character", "password"));
        }

        boolean lowerCase = false;
        boolean upperCase = false;
        boolean number = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (ch >= 'a' && ch <= 'z')
                lowerCase = true;

            if (ch >= 'A' && ch <= 'Z')
                upperCase = true;

            if (ch >= '0' && ch <= '9')
                number = true;
        }

        if (!lowerCase) {
            errors.add(new FieldValidationException().ofWithField("RegistrationDto", "Password should contain atleast one lowercase character", "password"));
        }

        if (!upperCase) {
            errors.add(new FieldValidationException().ofWithField("RegistrationDto", "Password should contain atleast one uppercase character", "password"));
        }

        if (!number) {
            errors.add(new FieldValidationException().ofWithField("RegistrationDto", "Password should contain atleast one number character", "password"));
        }
    }

    private void checkEmailErrors(String email, List<CustomExceptionModel> errors) {
        if (email == null) {
            errors.add(new FieldValidationException().ofWithField("RegistrationDto", "Email should not empty!", "email"));
            return;
        }

        if (email.length() > 6 && !email.matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")) {
            errors.add(new FieldValidationException().ofWithField("RegistrationDto", "Email should be in valid format!", "email"));
        }
    }

    private void checkUserNameErrors(String userName, List<CustomExceptionModel> errors) {
        if (userName == null) {
            errors.add(new FieldValidationException().ofWithField("RegistrationDto", "UserName should not empty!", "userName"));
            return;
        }
        if (userName.length() > 50) {
            errors.add(new FieldValidationException().ofWithField("RegistrationDto", "Name should not be greater than 50 character!", "userName"));
        }

    }
}
