package com.bookstore.wishlist.service;

import com.bookstore.wishlist.entity.WishlistItem;
import com.bookstore.wishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public List<WishlistItem> getWishlist(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public WishlistItem addItem(Long userId, Long productId) {
        return wishlistRepository.findByUserIdAndProductId(userId, productId)
                .orElseGet(() -> wishlistRepository.save(
                        WishlistItem.builder().userId(userId).productId(productId).build()
                ));
    }

    @Transactional
    public void removeItem(Long userId, Long productId) {
        wishlistRepository.deleteByUserIdAndProductId(userId, productId);
    }

    @Transactional
    public void clearWishlist(Long userId) {
        wishlistRepository.deleteAllByUserId(userId);
    }
}