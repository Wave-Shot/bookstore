package com.bookstore.admin.client;

import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @PutMapping("/api/products/{id}")
    Object updateProduct(@PathVariable Long id, @RequestBody ProductRequest req);

    @DeleteMapping("/api/products/{id}")
    void deleteProduct(@PathVariable Long id);

    @Data
    class ProductRequest {
        private String title;
        private String author;
        private BigDecimal price;
        private Integer stockQuantity;
    }
}