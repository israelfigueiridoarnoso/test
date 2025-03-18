package com.qindel.test.controller;

import com.qindel.test.dto.DTOConverter;
import com.qindel.test.dto.PriceDTO;
import com.qindel.test.entities.Price;
import com.qindel.test.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/getTopProductPrice")
    public ResponseEntity<PriceDTO> getTopProductPrice(@RequestParam Long productId, @RequestParam Long brandId, @RequestParam LocalDateTime date) {

        // Get the product Price
         Price price = priceService.getTopProductPrice(productId, brandId, date);

         // Convert Price to PriceDTO
        PriceDTO response = DTOConverter.toDto(price);

        // TODO: ¿Handle custom ERROR message?
         return ResponseEntity.ok(response);
    }

}
