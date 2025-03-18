package com.qindel.test.service;

import com.qindel.test.entities.Price;

import java.time.LocalDateTime;

public interface PriceService {

    Price getTopProductPrice(Long productId, Long brandId, LocalDateTime date);

}
