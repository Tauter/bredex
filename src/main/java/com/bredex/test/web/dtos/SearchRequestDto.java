package com.bredex.test.web.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchRequestDto {
    String brand;
    String type;
    Long price;
}
