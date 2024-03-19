package com.bredex.test.services.validators;

import com.bredex.test.web.dtos.AdCreationDto;
import com.bredex.test.web.errors.CustomExceptionModel;
import com.bredex.test.web.errors.ValidationExceptionModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class AdCreationDtoValidatorTest {

    private final AdCreationDtoValidator validator = new AdCreationDtoValidator();

    @Test
    void validate() {
        AdCreationDto data = AdCreationDto.builder().brand("Skoda").type("Octavia").description("1.6").price(12L).build();

        List<CustomExceptionModel> errors = validator.validate(data);

        Assertions.assertTrue(errors.isEmpty());

    }

    @Test
    void validateBrandEmpty() {
        AdCreationDto data = AdCreationDto.builder().type("Octavia").description("1.6").price(12L).build();

        List<CustomExceptionModel> errors = validator.validate(data);

        Assertions.assertEquals(1, errors.size());

        Assertions.assertEquals("brand", ((ValidationExceptionModel) errors.get(0)).getField());

    }

    @Test
    void validateBrandTooLong() {
        AdCreationDto data = AdCreationDto.builder().brand("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
                .type("Octavia")
                .description("1.6")
                .price(12L)
                .build();

        List<CustomExceptionModel> errors = validator.validate(data);

        Assertions.assertEquals(1, errors.size());

        Assertions.assertEquals("brand", ((ValidationExceptionModel) errors.get(0)).getField());
    }

    @Test
    void validateTypeEmpty() {
        AdCreationDto data = AdCreationDto.builder().brand("Skoda").description("1.6").price(12L).build();

        List<CustomExceptionModel> errors = validator.validate(data);

        Assertions.assertEquals(1, errors.size());

        Assertions.assertEquals("type", ((ValidationExceptionModel) errors.get(0)).getField());
    }
    @Test
    void validateTypeTooLong() {
        AdCreationDto data = AdCreationDto.builder().brand("Skoda").description("1.6").price(12L).type("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").build();

        List<CustomExceptionModel> errors = validator.validate(data);

        Assertions.assertEquals(1, errors.size());

        Assertions.assertEquals("type", ((ValidationExceptionModel) errors.get(0)).getField());
    }

    @Test
    void validateDescriptionEmpty() {
        AdCreationDto data = AdCreationDto.builder().brand("Skoda").price(12L).type("Octavia").build();

        List<CustomExceptionModel> errors = validator.validate(data);

        Assertions.assertEquals(1, errors.size());

        Assertions.assertEquals("description", ((ValidationExceptionModel) errors.get(0)).getField());
    }
    @Test
    void validateDescriptionTooBig() {

        char[] desc = new char[201];

        Arrays.fill(desc, 'X');
        String description = new String(desc);


        AdCreationDto data = AdCreationDto.builder().brand("Skoda").price(12L).type("Octavia").description(description).build();

        List<CustomExceptionModel> errors = validator.validate(data);

        Assertions.assertEquals(1, errors.size());

        Assertions.assertEquals("description", ((ValidationExceptionModel) errors.get(0)).getField());
    }

    @Test
    void validatePriceEmpty() {

        AdCreationDto data = AdCreationDto.builder().brand("Skoda").type("Octavia").description("1.6").build();

        List<CustomExceptionModel> errors = validator.validate(data);

        Assertions.assertEquals(1, errors.size());

        Assertions.assertEquals("price", ((ValidationExceptionModel) errors.get(0)).getField());
    }
    @Test
    void validatePriceTooBig() {

        AdCreationDto data = AdCreationDto.builder().brand("Skoda").type("Octavia").description("1.6").price(12345678901L).build();

        List<CustomExceptionModel> errors = validator.validate(data);

        Assertions.assertEquals(1, errors.size());

        Assertions.assertEquals("price", ((ValidationExceptionModel) errors.get(0)).getField());
    }
}
