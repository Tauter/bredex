package com.bredex.test.web.errors;

public class UserAlreadyExistException extends Exception implements ICustomException {

    @Override
    public CustomExceptionModel of(String email) {
        return new CustomExceptionModel("UserAccount", "User already exist with this email: " + email);
    }
}

