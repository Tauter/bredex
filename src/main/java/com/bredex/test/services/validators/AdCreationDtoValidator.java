package com.bredex.test.services.validators;

import com.bredex.test.web.dtos.AdCreationDto;
import com.bredex.test.web.errors.CustomExceptionModel;
import com.bredex.test.web.errors.FieldValidationException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class AdCreationDtoValidator {

    public List<CustomExceptionModel> validate(AdCreationDto adCreationDto) {
        List<CustomExceptionModel> errors = new LinkedList<>();

        this.checkBrandError(adCreationDto.getBrand(), errors);

        this.checkTypeError(adCreationDto.getType(), errors);

        this.checkDescriptionError(adCreationDto.getDescription(), errors);

        this.checkPriceError(adCreationDto.getPrice(), errors);

        return errors;
    }

    private void checkPriceError(Long price, List<CustomExceptionModel> errors) {
        if (price == null) {
            errors.add(new FieldValidationException().ofWithField("AdCreationDto", "Price should not be empty!", "price"));
            return;
        }

        if (price.toString().length() > 10)
            errors.add(new FieldValidationException().ofWithField("AdCreationDto", "Price should not be greater than 10 character!", "price"));

    }

    private void checkDescriptionError(String description, List<CustomExceptionModel> errors) {
        if (description == null) {
            errors.add(new FieldValidationException().ofWithField("AdCreationDto", "Description should not be empty!", "description"));
            return;
        }

        if (description.length() > 20)
            errors.add(new FieldValidationException().ofWithField("AdCreationDto", "Description should not be greater than 200 character!", "description"));

    }

    private void checkTypeError(String type, List<CustomExceptionModel> errors) {
        if (type == null || type.isEmpty()) {
            errors.add(new FieldValidationException().ofWithField("AdCreationDto", "Type should not be empty!", "type"));
            return;
        }

        if (type.length() > 20)
            errors.add(new FieldValidationException().ofWithField("AdCreationDto", "Type should not be greater than 20 character!", "type"));

    }

    private void checkBrandError(String brand, List<CustomExceptionModel> errors) {
        if (brand == null || brand.isEmpty()) {
            errors.add(new FieldValidationException().ofWithField("AdCreationDto", "Brand should not be empty!", "brand"));
            return;
        }

        if (brand.length() > 20)
            errors.add(new FieldValidationException().ofWithField("AdCreationDto", "Brand should not be greater than 20 character!", "brand"));

    }
}
