package com.qindel.test.dto;

import com.qindel.test.entities.Price;

public class DTOConverter {

    public static PriceDTO toDto(Price price) {
        return new PriceDTO(
                price.getProduct().getId(),
                price.getBrand().getId(),
                price.getPriceRate(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice(),
                price.getCurrency()
        );
    }

}
