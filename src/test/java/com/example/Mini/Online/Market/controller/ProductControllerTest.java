package com.example.Mini.Online.Market.controller;

import com.example.Mini.Online.Market.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Test
    void getReviewsOfProduct() {
        when(productService.getProductReview(anyLong())).thenReturn(Arrays.asList());
    }
}