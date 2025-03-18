package com.qindel.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO {

    private Long productId;
    private Long brandId;
    private Integer priceRate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
    private String currency;

}
