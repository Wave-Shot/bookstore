package com.bookstore.customer.service;

import com.bookstore.customer.entity.*;
import com.bookstore.customer.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerProfileRepository profileRepository;
    private final AddressRepository addressRepository;

    public CustomerProfile getOrCreateProfile(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseGet(() -> profileRepository.save(
                        CustomerProfile.builder().userId(userId).build()));
    }

    public CustomerProfile updateProfile(Long userId, String phone, String preferences) {
        CustomerProfile profile = getOrCreateProfile(userId);
        profile.setPhone(phone);
        profile.setPreferences(preferences);
        return profileRepository.save(profile);
    }

    public Address addAddress(Long userId, Address address) {
        address.setUserId(userId);
        return addressRepository.save(address);
    }

    public List<Address> getAddresses(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }

    public Address setDefaultAddress(Long addressId, Long userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        addresses.forEach(a -> {
            a.setDefault(a.getId().equals(addressId));
            addressRepository.save(a);
        });
        return addressRepository.findById(addressId).orElseThrow();
    }
}