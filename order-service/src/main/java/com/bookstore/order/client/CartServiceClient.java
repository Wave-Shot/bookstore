package com.bookstore.order.client;

import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "cart-service")
public interface CartServiceClient {

    @GetMapping("/api/cart")
    CartResponse getCart(@RequestHeader("userId") String userId);

    @DeleteMapping("/api/cart/clear")
    void clearCart(@RequestHeader("userId") String userId);

    @Data
    class CartResponse {
        private String userId;
        private List<CartItem> items;
        private BigDecimal totalAmount;
    }

    @Data
    class CartItem {
        private Long productId;
        private String productTitle;
        private int quantity;
        private BigDecimal unitPrice;
    }
}