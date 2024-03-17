package com.bredex.test.web.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdCreationDto {

    private String brand;

    private String type;

    private String description;

    private Long price;
}
