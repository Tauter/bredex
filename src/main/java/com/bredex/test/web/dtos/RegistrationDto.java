package com.bredex.test.web.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.With;

import javax.validation.constraints.*;

@Builder
@Getter
@With
public class RegistrationDto {

    String userName;

    String email;

    String password;

}
