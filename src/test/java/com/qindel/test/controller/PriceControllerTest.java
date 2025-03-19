package com.qindel.test.controller;

import com.qindel.test.dto.DTOConverter;
import com.qindel.test.dto.PriceDTO;
import com.qindel.test.entities.Brand;
import com.qindel.test.entities.Price;
import com.qindel.test.entities.Product;
import com.qindel.test.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    private static final String ENDPOINT_URI = "/prices/getTopProductPrice";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    private Price testPrice;
    private PriceDTO testDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();

        // Create a test Product
        Product testProduct = new Product();
        testProduct.setId(35455L);

        //Create a test Brand
        Brand testBrand = new Brand();
        testBrand.setId(1L);

        // Create a test Price
        testPrice = new Price();
        testPrice.setId(1L);
        testPrice.setBrand(testBrand);
        testPrice.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        testPrice.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
        testPrice.setProduct(testProduct);
        testPrice.setPrice(BigDecimal.valueOf(35.50));
        testPrice.setCurrency("EUR");

        // Convert to a test PriceDTO
        testDto = DTOConverter.toDto(testPrice);
    }

    @Test
    void testGetTopProductPrice_NotFound() throws Exception {
        // Mock the service method to return empty
        Mockito.when(priceService.getTopProductPrice(any(Long.class), any(Long.class), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        // Simulate the HTTP GET Request and a 404 (NOT FOUND) Response
        mockMvc.perform(get(ENDPOINT_URI)
                .param("productId", "35455")
                .param("brandId", "1")
                .param("date", "2020-06-14T10:00:00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetTopProductPrice_OK() throws Exception {
        // Mock the service method to return empty
        Mockito.when(priceService.getTopProductPrice(any(Long.class), any(Long.class), any(LocalDateTime.class)))
                .thenReturn(Optional.of(testPrice));

        // Simulate the HTTP GET Request and a 200 (OK) Response
        mockMvc.perform(get(ENDPOINT_URI)
                .param("productId", "35455")
                .param("brandId", "1")
                .param("date", "2020-06-14T10:00:00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect((jsonPath("$.price").value(testDto.getPrice())))
                .andExpect(status().isOk());
    }

}