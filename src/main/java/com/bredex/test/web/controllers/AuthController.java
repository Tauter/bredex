package com.bredex.test.web.controllers;

import com.bredex.test.domain.mappers.IUserAccountMapper;
import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.services.IUserAccountService;
import com.bredex.test.web.dtos.RegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserAccountService userAccountService;

    private final IUserAccountMapper userAccountMapper;

    @PostMapping("/signup")
    public ResponseEntity<Void> SignUp(@Validated @RequestBody RegistrationDto registrationDto) {

        Optional<UserAccount> userByEmail = this.userAccountService.findByEmail(registrationDto.getEmail());

        if (userByEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Optional<UserAccount> savedUser = this.userAccountService.save(this.userAccountMapper.mapTo(registrationDto));

        if (savedUser.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }
}
