package com.bookstore.admin.controller;

import com.bookstore.admin.client.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductServiceClient productServiceClient;
    private final OrderServiceClient orderServiceClient;

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id,
                                                @RequestBody ProductServiceClient.ProductRequest req) {
        return ResponseEntity.ok(productServiceClient.updateProduct(id, req));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productServiceClient.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Object>> getAllOrders() {
        return ResponseEntity.ok(orderServiceClient.getAllOrders());
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<Object> updateOrderStatus(@PathVariable Long id,
                                                    @RequestParam String status) {
        return ResponseEntity.ok(orderServiceClient.updateOrderStatus(id, status));
    }
}