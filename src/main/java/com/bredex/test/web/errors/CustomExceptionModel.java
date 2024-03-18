package com.bredex.test.web.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomExceptionModel {

    @JsonProperty
    String className;

    @JsonProperty
    String message;

    public CustomExceptionModel(String className, String message) {
        this.className = className;
        this.message = message;
    }
}
