package com.bookstore.order.controller;

import com.bookstore.order.entity.*;
import com.bookstore.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(
            @RequestHeader("userId") Long userId,
            @RequestBody Order orderRequest) {
        orderRequest.setUserId(userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.placeOrderDirect(orderRequest));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getUserOrders(
            @RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(
            @PathVariable Long id,
            @RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(orderService.cancelOrder(id, userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }
}