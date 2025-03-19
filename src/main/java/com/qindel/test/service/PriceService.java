package com.qindel.test.service;

import com.qindel.test.entities.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {

    Optional<Price> getTopProductPrice(Long productId, Long brandId, LocalDateTime date);

}
