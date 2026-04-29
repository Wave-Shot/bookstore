package com.bookstore.productservice.controller;

import com.bookstore.productservice.entity.Category;
import com.bookstore.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Category> getAll() {
        return productService.getAllCategories();
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return productService.createCategory(category);
    }
}