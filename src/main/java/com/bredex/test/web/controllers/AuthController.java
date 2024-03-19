package com.bredex.test.web.controllers;

import com.bredex.test.domain.mappers.IUserAccountMapper;
import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.services.IUserAccountService;
import com.bredex.test.services.auth.ILogOutService;
import com.bredex.test.services.auth.JwtUtilsService;
import com.bredex.test.services.validators.RegistrationDtoValidator;
import com.bredex.test.web.dtos.JwtResponseDto;
import com.bredex.test.web.dtos.RegistrationDto;
import com.bredex.test.web.errors.CustomExceptionModel;
import com.bredex.test.web.errors.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final IUserAccountService userAccountService;

    private final IUserAccountMapper userAccountMapper;

    private final RegistrationDtoValidator registrationDtoValidator;

    private final JwtUtilsService jwtUtilsService;

    private final ILogOutService logOutService;


    @PostMapping("/signup")
    public ResponseEntity<Object> SignUp(@RequestBody RegistrationDto registrationDto) {

        List<CustomExceptionModel> validationErrors = registrationDtoValidator.validate(registrationDto);

        if (!validationErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(validationErrors);
        }

        Optional<UserAccount> userByEmail = this.userAccountService.findByEmail(registrationDto.getEmail());

        if (userByEmail.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new UserAlreadyExistException().of(registrationDto.getEmail()));
        }

        this.userAccountService.save(this.userAccountMapper.mapTo(registrationDto));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestParam String email, @RequestParam String password) {
        Optional<UserAccount> foundEmail = this.userAccountService.findByEmail(email);

        if (foundEmail.isEmpty())
            return ResponseEntity.notFound().build();

        if (!foundEmail.get().getPassword().equals(password))
            return ResponseEntity.notFound().build();

        JwtResponseDto accessTokens = this.jwtUtilsService.generate(foundEmail.get());

        return ResponseEntity.ok(accessTokens);

    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(Principal principal) {

        Optional<UserAccount> userAccount = this.userAccountService.findByEmail(principal.getName());

        if (userAccount.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        this.logOutService.logout(userAccount.get());

        return ResponseEntity.ok().build();
    }
}
