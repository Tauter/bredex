package com.bredex.test.domain.mappers;

import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.web.dtos.RegistrationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserAccountMapper {

    UserAccount mapTo(RegistrationDto registrationDto);
}
