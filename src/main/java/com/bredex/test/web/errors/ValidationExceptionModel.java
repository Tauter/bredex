package com.bredex.test.web.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationExceptionModel extends CustomExceptionModel {

    @JsonProperty
    String field;


    public ValidationExceptionModel(String className, String message, String field) {
        super(className, message);
        this.field = field;
    }
}

