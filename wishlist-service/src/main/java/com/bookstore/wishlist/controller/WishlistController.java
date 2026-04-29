package com.bookstore.wishlist.controller;

import com.bookstore.wishlist.entity.WishlistItem;
import com.bookstore.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<List<WishlistItem>> getWishlist(@RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(wishlistService.getWishlist(userId));
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<WishlistItem> addItem(@RequestHeader("userId") Long userId,
                                                @PathVariable Long productId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(wishlistService.addItem(userId, productId));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Void> removeItem(@RequestHeader("userId") Long userId,
                                           @PathVariable Long productId) {
        wishlistService.removeItem(userId, productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearWishlist(@RequestHeader("userId") Long userId) {
        wishlistService.clearWishlist(userId);
        return ResponseEntity.noContent().build();
    }
}