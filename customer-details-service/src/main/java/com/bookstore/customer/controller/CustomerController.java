package com.bookstore.customer.controller;

import com.bookstore.customer.entity.*;
import com.bookstore.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/details")
    public ResponseEntity<CustomerProfile> getDetails(@RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(customerService.getOrCreateProfile(userId));
    }

    @PostMapping("/details")
    public ResponseEntity<CustomerProfile> createProfile(@RequestHeader("userId") Long userId,
                                                         @RequestParam(required = false) String phone,
                                                         @RequestParam(required = false) String preferences) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.updateProfile(userId, phone, preferences));
    }

    @PutMapping("/details")
    public ResponseEntity<CustomerProfile> updateProfile(@RequestHeader("userId") Long userId,
                                                         @RequestParam(required = false) String phone,
                                                         @RequestParam(required = false) String preferences) {
        return ResponseEntity.ok(customerService.updateProfile(userId, phone, preferences));
    }

    @PostMapping("/addresses")
    public ResponseEntity<Address> addAddress(@RequestHeader("userId") Long userId,
                                              @RequestBody Address address) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.addAddress(userId, address));
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAddresses(@RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(customerService.getAddresses(userId));
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        customerService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/addresses/{id}/default")
    public ResponseEntity<Address> setDefault(@PathVariable Long id,
                                              @RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(customerService.setDefaultAddress(id, userId));
    }
}