package com.qindel.test.entities;

import jakarta.persistence.*;
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
@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    // NOTE: In case of scaling, another entity (e.g., PriceRate) could be created (with fields like name,
    // description, etc.), but for simplicity, it was implemented just as a field for this test
    private Integer priceRate;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer priority;

    private BigDecimal price;

    private String currency;

}
