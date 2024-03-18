package com.bredex.test.web.errors;

public class UserNotFoundException extends Exception implements ICustomException {


    @Override
    public ClassException of(String email) {
        return new ClassException("UserAccount","User not found with this email: " + email);
    }

}
