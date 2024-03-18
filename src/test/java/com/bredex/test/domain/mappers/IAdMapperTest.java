package com.bredex.test.domain.mappers;

import com.bredex.test.domain.models.Ad;
import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.web.dtos.AdCreationDto;
import com.bredex.test.web.dtos.AdResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IAdMapperTest {

    private final IAdMapper mapper = new IAdMapperImpl();

    @Test
    void mapFromCreation() {
        UserAccount user = UserAccount.builder().id(1L).build();

        AdCreationDto data = AdCreationDto.builder().brand("newBrand").type("newType").description("newDescription").price(1L).build();

        Ad expected = Ad.builder().id(null).userAccount(user).price(1L).brand("newBrand").type("newType").description("newDescription").build();

        Ad result = this.mapper.mapFromCreationDto(data, user);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void mapToResponse() {
        UserAccount user = UserAccount.builder().id(2L).build();

        Ad data = Ad.builder().brand("newBrand").type("newType").description("newDescription").price(1L).id(1L).userAccount(user).build();

        AdResponseDto expected = AdResponseDto.builder().id(1L).userAccountId(2L).price(1L).brand("newBrand").type("newType").description("newDescription").build();

        AdResponseDto result = this.mapper.mapToResponse(data);

        Assertions.assertEquals(expected, result);
    }
}
