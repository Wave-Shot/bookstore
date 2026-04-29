package com.bookstore.productservice.service;

import com.bookstore.productservice.entity.*;
import com.bookstore.productservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepo;
    @Autowired private CategoryRepository categoryRepo;

    public Page<Product> getAllProducts(int page, int size) {
        return productRepo.findAll(PageRequest.of(page, size));
    }

    public Product getById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    public List<Product> search(String query) {
        return productRepo.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
    }

    public List<Product> getByCategory(Long categoryId) {
        return productRepo.findByCategoryId(categoryId);
    }

    public Product create(Product product) {
        return productRepo.save(product);
    }

    public Product update(Long id, Product updated) {
        Product existing = getById(id);
        existing.setTitle(updated.getTitle());
        existing.setAuthor(updated.getAuthor());
        existing.setPrice(updated.getPrice());
        existing.setStockQuantity(updated.getStockQuantity());
        existing.setImageUrl(updated.getImageUrl());
        return productRepo.save(existing);
    }

    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }
}