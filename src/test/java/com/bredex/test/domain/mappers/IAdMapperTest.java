package com.bredex.test.domain.mappers;

import com.bredex.test.domain.models.Ad;
import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.web.dtos.AdCreationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IAdMapperTest {

    private final IAdMapper mapper = new IAdMapperImpl();

    @Test
    void mapTo() {
        UserAccount user = UserAccount.builder().id(1L).build();

        AdCreationDto data = AdCreationDto.builder().brand("newBrand").type("newType").description("newDescription").price(1L).build();

        Ad expected = Ad.builder().id(null).userAccount(user).price(1L).brand("newBrand").type("newType").description("newDescription").build();

        Ad result = this.mapper.mapFromCreationDto(data, user);

        Assertions.assertEquals(expected, result);
    }
}
