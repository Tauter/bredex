package com.bredex.test.web.controllers;

import com.bredex.test.domain.models.Ad;
import com.bredex.test.domain.repositories.IAdRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IAdRepository adRepository;

    @Test
    void createAd() {
    }

    @Test
    void deleteById() {

    }

    @Test
    void getById() {
        MvcResult mvcResult = Assertions.assertDoesNotThrow(() -> mockMvc.perform(get("/ad/1"))
                .andExpect(status().isOk())
                .andReturn());

        byte[] responseBytes = mvcResult.getResponse().getContentAsByteArray();

        Ad ad = Assertions.assertDoesNotThrow(() -> objectMapper.readValue(responseBytes, Ad.class));

        Assertions.assertEquals(1L, ad.getId());
        Assertions.assertEquals("Skoda", ad.getBrand());
        Assertions.assertEquals("Superb", ad.getType());
        Assertions.assertEquals("2.0 TDI", ad.getDescription());
        Assertions.assertEquals(1111L, ad.getPrice());
    }

    @Test
    void getByIdNotFound() {
        MvcResult mvcResult = Assertions.assertDoesNotThrow(() -> mockMvc.perform(get("/ad/0"))
                .andExpect(status().isNotFound())
                .andReturn());

        int responseLength = Assertions.assertDoesNotThrow(() -> mvcResult.getResponse().getContentLength());

        Assertions.assertEquals(0, responseLength);
    }

    @Test
    void search() {
    }

}
