package com.bookstore.order.service;

import com.bookstore.order.client.CartServiceClient;
import com.bookstore.order.entity.*;
import com.bookstore.order.exception.ResourceNotFoundException;
import com.bookstore.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartServiceClient cartServiceClient;
    private final OrderEventPublisher eventPublisher;

    public Order placeOrder(Long userId) {
        CartServiceClient.CartResponse cart = cartServiceClient.getCart(String.valueOf(userId));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = Order.builder()
                .userId(userId)
                .totalAmount(cart.getTotalAmount())
                .build();

        List<OrderItem> items = cart.getItems().stream()
                .map(i -> OrderItem.builder()
                        .productId(i.getProductId())
                        .productTitle(i.getProductTitle())
                        .quantity(i.getQuantity())
                        .unitPrice(i.getUnitPrice())
                        .order(order)
                        .build())
                .collect(Collectors.toList());

        order.setItems(items);
        Order saved = orderRepository.save(order);

        cartServiceClient.clearCart(String.valueOf(userId));
        eventPublisher.publishOrderPlaced(saved);

        return saved;
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
    }

    public Order cancelOrder(Long id, Long userId) {
        Order order = getOrderById(id);
        if (!order.getUserId().equals(userId)) throw new RuntimeException("Unauthorized");
        if (order.getStatus() != OrderStatus.PENDING)
            throw new RuntimeException("Only pending orders can be cancelled");
        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateStatus(Long id, OrderStatus status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        Order saved = orderRepository.save(order);
        eventPublisher.publishOrderStatusChanged(saved);
        return saved;
    }

    public Order placeOrderDirect(Order order) {
        if (order.getItems() != null) {
            order.getItems().forEach(item -> item.setOrder(order));
        }
        Order saved = orderRepository.save(order);
        eventPublisher.publishOrderPlaced(saved);
        return saved;
    }
}