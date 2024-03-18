package com.bredex.test.web.dtos;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class AdResponseDto {

    private Long id;

    private Long userAccountId;

    private String brand;

    private String type;

    private String description;

    private Long price;
}
