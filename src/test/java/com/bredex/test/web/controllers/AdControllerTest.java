package com.bredex.test.web.controllers;

import com.bredex.test.domain.models.Ad;
import com.bredex.test.domain.repositories.IAdRepository;
import com.bredex.test.web.dtos.AdCreationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IAdRepository adRepository;

    @Test
    void getById() {
        MvcResult mvcResult = Assertions.assertDoesNotThrow(() -> mockMvc.perform(get("/ad/1").with(user("email@gmail.com")))
                .andExpect(status().isOk())
                .andReturn());

        byte[] responseBytes = mvcResult.getResponse().getContentAsByteArray();

        Ad ad = Assertions.assertDoesNotThrow(() -> objectMapper.readValue(responseBytes, Ad.class));

        Assertions.assertEquals(1L, ad.getId());
        Assertions.assertEquals("Skoda", ad.getBrand());
        Assertions.assertEquals("Superb", ad.getType());
        Assertions.assertEquals("2.0 TDI", ad.getDescription());
        Assertions.assertEquals(1L, ad.getPrice());
    }

    @Test
    void getByIdNotFound() {
        MvcResult mvcResult = Assertions.assertDoesNotThrow(() -> mockMvc.perform(get("/ad/0").with(user("email@gmail.com")))
                .andExpect(status().isNotFound())
                .andReturn());

        int responseLength = Assertions.assertDoesNotThrow(() -> mvcResult.getResponse().getContentLength());

        Assertions.assertEquals(0, responseLength);
    }

    @Test
    void search() {
        int baseCount = this.adRepository.findAll().size();

        MvcResult mvcResult = Assertions.assertDoesNotThrow(() ->
                mockMvc.perform(get("/ad/search")
                                .queryParam("brand", "Au")
                                .with(user("email@gmail.com"))
                        )
                        .andExpect(status().isOk())
                        .andReturn());

        byte[] contentAsByteArray = mvcResult.getResponse().getContentAsByteArray();
        List result = Assertions.assertDoesNotThrow(() -> this.objectMapper.readValue(contentAsByteArray, List.class));

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("localhost:8080/ad/3", result.get(0));
        Assertions.assertEquals("localhost:8080/ad/4", result.get(1));
    }

    @Test
    @DirtiesContext
    void createAd() {
        int baseCount = this.adRepository.findAll().size();
        AdCreationDto body = AdCreationDto.builder().brand("skoda").type("octavia").price(1L).description("asd").build();
        String bodyString = Assertions.assertDoesNotThrow(() -> this.objectMapper.writeValueAsString(body));
        MvcResult mvcResult = Assertions.assertDoesNotThrow(() -> mockMvc.perform(post("/ad")
                        .with(user("email2@gmail.com"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyString)
                )
                .andExpect(status().isCreated())
                .andReturn());

        byte[] contentAsByteArray = mvcResult.getResponse().getContentAsByteArray();
        Long savedObjectId = Assertions.assertDoesNotThrow(() -> this.objectMapper.readValue(contentAsByteArray, Long.class));

        Assertions.assertEquals(baseCount + 1, this.adRepository.findAll().size());
        Assertions.assertEquals(baseCount + 1, savedObjectId);

    }

    @Test
    @DirtiesContext
    void deleteById() {
        int baseCount = this.adRepository.findAll().size();
        MvcResult mvcResult = Assertions.assertDoesNotThrow(() -> mockMvc.perform(delete("/ad/8")
                        .with(user("email2@gmail.com")))
                .andExpect(status().isNoContent())
                .andReturn());

        int contentLength = mvcResult.getResponse().getContentLength();

        Assertions.assertEquals(baseCount - 1, this.adRepository.findAll().size());
        Assertions.assertEquals(0, contentLength);

    }

    @Test
    void deleteByIdWrongUser() {
        int baseCount = this.adRepository.findAll().size();
        MvcResult mvcResult = Assertions.assertDoesNotThrow(() -> mockMvc.perform(delete("/ad/1")
                        .with(user("email2@gmail.com")))
                .andExpect(status().isMethodNotAllowed())
                .andReturn());

        int contentLength = mvcResult.getResponse().getContentLength();

        Assertions.assertEquals(0, contentLength);
        Assertions.assertEquals(baseCount, this.adRepository.findAll().size());
    }

    @Test
    void deleteByIdNotFound() {
        int baseCount = this.adRepository.findAll().size();
        MvcResult mvcResult = Assertions.assertDoesNotThrow(() -> mockMvc.perform(delete("/ad/0")
                        .with(user("email2@gmail.com")))
                .andExpect(status().isNotFound())
                .andReturn());

        int contentLength = mvcResult.getResponse().getContentLength();

        Assertions.assertEquals(0, contentLength);
        Assertions.assertEquals(baseCount, this.adRepository.findAll().size());
    }
}
