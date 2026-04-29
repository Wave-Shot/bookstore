package com.bookstore.productservice.controller;

import com.bookstore.productservice.entity.*;
import com.bookstore.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Products", description = "Product management APIs")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Get all products (paginated)")
    @GetMapping
    public Page<Product> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getAllProducts(page, size);
    }

    @Operation(summary = "Get product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @Operation(summary = "Search products by title or author")
    @GetMapping("/search")
    public List<Product> search(@RequestParam String q) {
        return productService.search(q);
    }

    @Operation(summary = "Get products by category")
    @GetMapping("/category/{id}")
    public List<Product> getByCategory(@PathVariable Long id) {
        return productService.getByCategory(id);
    }

    @Operation(summary = "Create a product (Admin only)")
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(productService.create(product));
    }

    @Operation(summary = "Update a product (Admin only)")
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id,
                                          @RequestBody Product product) {
        return ResponseEntity.ok(productService.update(id, product));
    }

    @Operation(summary = "Delete a product (Admin only)")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}