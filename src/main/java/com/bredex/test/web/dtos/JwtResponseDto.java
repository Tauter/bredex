package com.bredex.test.web.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Jacksonized
public class JwtResponseDto {

    @JsonProperty
    private String accessToken;
    @JsonProperty
    private String refreshToken;
}
