package com.qindel.test.service;

import com.qindel.test.entities.Price;
import com.qindel.test.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    // Get the product Price with the highest priority
    @Override
    public Optional<Price> getTopProductPrice(Long productId, Long brandId, LocalDateTime date) {
        return priceRepository.findTopPriorityPrice(productId, brandId, date);
    }
}
