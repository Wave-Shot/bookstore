package com.bookstore.cart.service;

import com.bookstore.cart.client.ProductServiceClient;
import com.bookstore.cart.dto.AddToCartRequest;
import com.bookstore.cart.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ProductServiceClient productServiceClient;

    private static final String KEY_PREFIX = "cart:";

    public Cart getCart(String userId) {
        Object raw = redisTemplate.opsForValue().get(KEY_PREFIX + userId);
        if (raw == null) return new Cart();
        if (raw instanceof Cart) return (Cart) raw;
        // Handle LinkedHashMap deserialization
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(raw, Cart.class);
    }

    public Cart addItem(String userId, AddToCartRequest request) {
        Cart cart = getCart(userId);
        cart.setUserId(userId);

        ProductServiceClient.ProductResponse product =
                productServiceClient.getProduct(request.getProductId());

        Optional<CartItem> existing = cart.getItems().stream()
                .filter(i -> i.getProductId().equals(request.getProductId()))
                .findFirst();

        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + request.getQuantity());
        } else {
            cart.getItems().add(new CartItem(
                    product.getId(), product.getTitle(),
                    request.getQuantity(), product.getPrice()
            ));
        }

        redisTemplate.opsForValue().set(KEY_PREFIX + userId, cart);
        return cart;
    }

    public Cart updateItem(String userId, Long productId, int quantity) {
        Cart cart = getCart(userId);
        cart.getItems().stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .ifPresent(i -> i.setQuantity(quantity));
        redisTemplate.opsForValue().set(KEY_PREFIX + userId, cart);
        return cart;
    }

    public Cart removeItem(String userId, Long productId) {
        Cart cart = getCart(userId);
        cart.getItems().removeIf(i -> i.getProductId().equals(productId));
        redisTemplate.opsForValue().set(KEY_PREFIX + userId, cart);
        return cart;
    }

    public void clearCart(String userId) {
        redisTemplate.delete(KEY_PREFIX + userId);
    }

    public BigDecimal getTotal(String userId) {
        return getCart(userId).getTotalAmount();
    }
}