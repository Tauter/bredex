package com.bredex.test.domain.mappers;

import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.web.dtos.RegistrationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IUserAccountMapperTest {

    private final IUserAccountMapper mapper = new IUserAccountMapperImpl();

    @Test
    void mapTo() {
        RegistrationDto data = RegistrationDto.builder().email("testEmail").userName("testEmail").password("testPassword").build();

        UserAccount expected = UserAccount.builder().email("testEmail").userName("testEmail").password("testPassword").build();

        Assertions.assertEquals(expected, this.mapper.mapTo(data));
    }
}
