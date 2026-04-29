package com.bookstore.notification.consumer;

import com.bookstore.notification.event.OrderEvent;
import com.bookstore.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void onOrderEvent(OrderEvent event) {
        log.info("Received order event: {}", event.getType());
        switch (event.getType()) {
            case "ORDER_PLACED"     -> emailService.sendOrderConfirmation(event);
            case "ORDER_SHIPPED"    -> emailService.sendShippingUpdate(event);
            case "ORDER_DELIVERED"  -> emailService.sendDeliveryConfirmation(event);
            default -> log.info("Unhandled event type: {}", event.getType());
        }
    }
}