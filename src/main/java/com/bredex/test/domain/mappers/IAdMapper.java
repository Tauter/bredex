package com.bredex.test.domain.mappers;

import com.bredex.test.domain.models.Ad;
import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.web.dtos.AdCreationDto;
import com.bredex.test.web.dtos.AdResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAdMapper {

    @Mapping(target = "id", ignore = true)
    Ad mapFromCreationDto(AdCreationDto dto, UserAccount userAccount);

    @Mapping(target = "userAccountId", source = "ad.userAccount.id")
    AdResponseDto mapToResponse(Ad ad);
}
