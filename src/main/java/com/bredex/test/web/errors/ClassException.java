package com.bredex.test.web.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@Getter
public class ClassException {

    @JsonProperty
    String className;

    @JsonProperty
    String message;
}
