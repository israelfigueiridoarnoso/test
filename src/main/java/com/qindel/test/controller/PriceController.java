package com.qindel.test.controller;

import com.qindel.test.dto.DTOConverter;
import com.qindel.test.dto.PriceDTO;
import com.qindel.test.entities.Price;
import com.qindel.test.service.PriceService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/getTopProductPrice")
    public ResponseEntity<PriceDTO> getTopProductPrice(@RequestParam @NotNull Long productId,
                                                       @RequestParam @NotNull Long brandId,
                                                       @RequestParam @NotNull LocalDateTime date) {

            // Get the product Price
            Optional<Price> price = priceService.getTopProductPrice(productId, brandId, date);

            // If there's no matching price -> NOT FOUND (404) message
            if (price.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // Convert Price to PriceDTO
            PriceDTO response = DTOConverter.toDto(price.get());

            return ResponseEntity.ok(response);

    }

}
