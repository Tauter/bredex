package com.bredex.test.web.errors;

public class FieldValidationException extends Exception implements ICustomException {
    public ValidationExceptionModel ofWithField(String className, String validationError, String field) {
        return new ValidationExceptionModel(className, "User not found with this email: " + validationError, field);
    }

    @Override
    public CustomExceptionModel of(String message) {
        return new CustomExceptionModel("Validation error!", message);
    }
}
