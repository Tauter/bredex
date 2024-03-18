package com.bredex.test.web.errors;

public class UserAlreadyExistException extends Exception implements ICustomException {

    @Override
    public ClassException of(String email) {
        return new ClassException("UserAccount", "User already exist with this email: " + email);
    }
}

