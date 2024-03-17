package com.bredex.test.domain.mappers;

import com.bredex.test.domain.models.Ad;
import com.bredex.test.domain.models.UserAccount;
import com.bredex.test.web.dtos.AdCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAdMapper {

    @Mapping(target = "brand", source = "dto.brand")
    @Mapping(target = "type", source = "dto.type")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "price", source = "dto.price")
    @Mapping(target = "userAccount", source = "userAccount")
    @Mapping(target = "id", ignore = true)
    Ad mapFromCreationDto(AdCreationDto dto, UserAccount userAccount);
}
