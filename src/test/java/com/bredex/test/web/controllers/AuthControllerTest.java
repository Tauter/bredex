package com.bredex.test.web.controllers;

import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.domain.repositories.IUserAccountRepository;
import com.bredex.test.web.dtos.RegistrationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class AuthControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private IUserAccountRepository repository;

    @Test
    void signUp() {

        int userAccountCount = this.repository.findAll().size();

        RegistrationDto body = RegistrationDto.builder()
                .email("brandNewEmail")
                .userName("brandNewName")
                .password("brandNewEmail")
                .build();

        this.webTestClient.post()
                .uri("/auth/signup")
                .bodyValue(body)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CREATED)
                .expectBody().isEmpty();

        Assertions.assertEquals(userAccountCount + 1, this.repository.findAll().size());

        UserAccount savedUser = this.repository.findAll().get(userAccountCount);

        Assertions.assertEquals(body.getEmail(), savedUser.getEmail());
        Assertions.assertEquals(body.getUserName(), savedUser.getUserName());
        Assertions.assertEquals(body.getPassword(), savedUser.getPassword());
        Assertions.assertNotNull(savedUser.getId());
    }
}
