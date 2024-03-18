package com.bredex.test.web.dtos;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Size;

@Builder
@Getter
public class SearchRequestDto {
    @Size(max = 20, message = "Brand should not be greater than 20 character!")
    String brand;
    @Size(max = 20, message = "Type should not be greater than 20 character!")
    String type;
    Long price;
}
