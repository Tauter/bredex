package com.bredex.test.web.dtos;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;

@Builder
@Getter
public class AdCreationDto {

    @Size(max = 20, message = "Brand should not be greater than 20 character!")
    private String brand;

    @Size(max = 20, message = "Type should not be greater than 20 character!")
    private String type;

    @Size(max = 200, message = "Description should not be greater than 200 character!")
    private String description;

    @Range(max = 9999999999L, message = "Price should not be greater than 10 character!")
    private Long price;
}
