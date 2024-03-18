package com.bredex.test.web.errors;

public class UserNotFoundException extends Exception implements ICustomException {


    @Override
    public CustomExceptionModel of(String email) {
        return new CustomExceptionModel("UserAccount","User not found with this email: " + email);
    }

}
