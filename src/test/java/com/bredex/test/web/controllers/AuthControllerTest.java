package com.bredex.test.web.controllers;

import com.bredex.test.domain.repositories.IUserAccountRepository;
import com.bredex.test.web.dtos.JwtResponseDto;
import com.bredex.test.web.dtos.RegistrationDto;
import com.bredex.test.web.errors.ClassException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IUserAccountRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    //
//    @Test
//    void signUp() {
//
//        int userAccountCount = this.repository.findAll().size();
//
//        RegistrationDto body = RegistrationDto.builder()
//                .email("brandNewEmail")
//                .userName("brandNewName")
//                .password("brandNewEmail")
//                .build();
//
//        this.webTestClient.post()
//                .uri("/auth/signup")
//                .bodyValue(body)
//                .exchange()
//                .expectStatus().isEqualTo(HttpStatus.CREATED)
//                .expectBody().isEmpty();
//
//        Assertions.assertEquals(userAccountCount + 1, this.repository.findAll().size());
//
//        UserAccount savedUser = this.repository.findAll().get(userAccountCount);
//
//        Assertions.assertEquals(body.getEmail(), savedUser.getEmail());
//        Assertions.assertEquals(body.getUserName(), savedUser.getUserName());
//        Assertions.assertEquals(body.getPassword(), savedUser.getPassword());
//        Assertions.assertNotNull(savedUser.getId());
//    }
    @Test
    void signup() {

        RegistrationDto body = RegistrationDto.builder().email("asd@asd.com").userName("yes").password("aA12testTest").build();

        MvcResult mvcResult = Assertions.assertDoesNotThrow(() ->
                mockMvc.perform(post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(body))
                        )
                        .andExpect(status().isCreated())
                        .andReturn());

        byte[] responseBytes = mvcResult.getResponse().getContentAsByteArray();

        Assertions.assertEquals(0, responseBytes.length);
    }

    @Test
    void signupAlreadyExist() {

        RegistrationDto body = RegistrationDto.builder().email("email@gmail.com").userName("yes").password("aA12aaaaaa").build();

        MvcResult mvcResult = Assertions.assertDoesNotThrow(() ->
                mockMvc.perform(post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(body))
                        )
                        .andExpect(status().isConflict())
                        .andReturn());

        byte[] responseBytes = mvcResult.getResponse().getContentAsByteArray();

        ClassException jwt = Assertions.assertDoesNotThrow(() -> objectMapper.readValue(responseBytes, ClassException.class));

        Assertions.assertEquals("UserAccount", jwt.getClassName());
        Assertions.assertEquals("User already exist with this email: email@gmail.com", jwt.getMessage());

    }

    @Test
    void login() {

        MvcResult mvcResult = Assertions.assertDoesNotThrow(() ->
                mockMvc.perform(post("/auth/login")
                                .queryParam("email", "email@gmail.com")
                                .queryParam("password", "password")
                        )
                        .andExpect(status().isOk())
                        .andReturn());

        byte[] responseBytes = mvcResult.getResponse().getContentAsByteArray();

        JwtResponseDto jwt = Assertions.assertDoesNotThrow(() -> objectMapper.readValue(responseBytes, JwtResponseDto.class));

        Assertions.assertNotNull(jwt);


    }
}
