package com.bredex.test.web.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.With;

import javax.validation.constraints.*;

@Builder
@Getter
@With
public class RegistrationDto {

    @Max(value = 50, message = "Name should not be greater than 50 character!")
    @NotEmpty
    String userName;

    @Email(message = "Email should be in valid format!")
    String email;

    @Min(value = 8, message = "Password length should be minimum 8 character")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)")
    String password;

}
