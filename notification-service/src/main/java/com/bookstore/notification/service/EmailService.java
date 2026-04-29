package com.bookstore.notification.service;

import com.bookstore.notification.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    public void sendOrderConfirmation(OrderEvent event) {
        // In production, wire up JavaMailSender here
        log.info("📧 Sending order confirmation for Order #{} to User #{}",
                event.getOrderId(), event.getUserId());
    }

    public void sendShippingUpdate(OrderEvent event) {
        log.info("📦 Sending shipping update for Order #{}", event.getOrderId());
    }

    public void sendDeliveryConfirmation(OrderEvent event) {
        log.info("✅ Sending delivery confirmation for Order #{}", event.getOrderId());
    }
}