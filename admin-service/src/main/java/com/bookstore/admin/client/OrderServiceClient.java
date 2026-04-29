package com.bookstore.admin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @GetMapping("/api/orders/all")
    List<Object> getAllOrders();

    @PutMapping("/api/orders/{id}/status")
    Object updateOrderStatus(@PathVariable Long id, @RequestParam String status);
}