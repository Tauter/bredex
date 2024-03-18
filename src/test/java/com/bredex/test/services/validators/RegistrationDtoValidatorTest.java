package com.bredex.test.services.validators;

import com.bredex.test.web.dtos.RegistrationDto;
import com.bredex.test.web.errors.CustomExceptionModel;
import com.bredex.test.web.errors.ValidationExceptionModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class RegistrationDtoValidatorTest {

    private final RegistrationDtoValidator validator = new RegistrationDtoValidator();

    @Test
    void validate() {
        RegistrationDto data = RegistrationDto.builder().userName("asd").email("asd@sasd.ca").password("asd123ASDTEST").build();
        List<CustomExceptionModel> errors = validator.validate(data);

        Assertions.assertTrue(errors.isEmpty());
    }

    @Test
    void validateEmailGood() {
        RegistrationDto data = RegistrationDto.builder().userName("asd").email("asd@.ca").password("asd123ASDTEST").build();
        List<CustomExceptionModel> errors = validator.validate(data);


        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("email", ((ValidationExceptionModel) errors.get(0)).getField());
    }

    @Test
    void validateEmailFirstPart() {
        RegistrationDto data = RegistrationDto.builder().userName("asd").email("@asd.ca").password("asd123ASDTEST").build();
        List<CustomExceptionModel> errors = validator.validate(data);


        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("email", ((ValidationExceptionModel) errors.get(0)).getField());
    }

    @Test
    void validateEmailSecondPart() {
        RegistrationDto data = RegistrationDto.builder().userName("asd").email("asd@.ca").password("asd123ASDTEST").build();
        List<CustomExceptionModel> errors = validator.validate(data);


        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("email", ((ValidationExceptionModel) errors.get(0)).getField());
    }

    @Test
    void validateEmailThirdPart() {
        RegistrationDto data = RegistrationDto.builder().userName("asd").email("asd@asd").password("asd123ASDTEST").build();
        List<CustomExceptionModel> errors = validator.validate(data);


        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("email", ((ValidationExceptionModel) errors.get(0)).getField());
    }

    @Test
    void validateEmailEmpty() {
        RegistrationDto data = RegistrationDto.builder().userName("asd").password("asd123ASDTEST").build();
        List<CustomExceptionModel> errors = validator.validate(data);


        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("email", ((ValidationExceptionModel) errors.get(0)).getField());
    }

    @Test
    void validateUserNameEmpty() {
        RegistrationDto data = RegistrationDto.builder().email("asd@asd.com").password("asd123ASDTEST").build();
        List<CustomExceptionModel> errors = validator.validate(data);


        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("userName", ((ValidationExceptionModel) errors.get(0)).getField());
    }

    @Test
    void validateUserNameTooBig() {
        RegistrationDto data = RegistrationDto.builder()
                .userName("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
                .email("asd@asd.com")
                .password("asd123ASDTEST")
                .build();
        List<CustomExceptionModel> errors = validator.validate(data);


        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("userName", ((ValidationExceptionModel) errors.get(0)).getField());
    }

    @Test
    void validatePasswordEmpty() {
        RegistrationDto data = RegistrationDto.builder().userName("asdasdasd").email("asd@asd.com").build();
        List<CustomExceptionModel> errors = validator.validate(data);


        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("password", ((ValidationExceptionModel) errors.get(0)).getField());
    }

    @Test
    void validatePasswordNoNumber() {
        RegistrationDto data = RegistrationDto.builder().userName("asdasdasd").email("asd@asd.com").password("qweasdZXC").build();
        List<CustomExceptionModel> errors = validator.validate(data);


        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("password", ((ValidationExceptionModel) errors.get(0)).getField());
        Assertions.assertEquals(
                "User not found with this email: Password should contain atleast one number character",
                errors.get(0).getMessage()
        );
    }

    @Test
    void validatePasswordNoUpperCase() {
        RegistrationDto data = RegistrationDto.builder().userName("asdasdasd").email("asd@asd.com").password("qweasd123").build();
        List<CustomExceptionModel> errors = validator.validate(data);


        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("password", ((ValidationExceptionModel) errors.get(0)).getField());
        Assertions.assertEquals(
                "User not found with this email: Password should contain atleast one uppercase character",
                errors.get(0).getMessage()
        );
    }

    @Test
    void validatePasswordNoLowerCase() {
        RegistrationDto data = RegistrationDto.builder().userName("asdasdasd").email("asd@asd.com").password("QWEASD123").build();
        List<CustomExceptionModel> errors = validator.validate(data);


        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("password", ((ValidationExceptionModel) errors.get(0)).getField());
        Assertions.assertEquals(
                "User not found with this email: Password should contain atleast one lowercase character",
                errors.get(0).getMessage()
        );
    }
}
