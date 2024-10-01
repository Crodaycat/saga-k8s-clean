package com.food.ordering.system.payment.service.domain.ports.output.respository;

import java.util.Optional;
import java.util.UUID;

import com.food.ordering.system.payment.service.domain.entity.Payment;

public interface PaymentRepository {
    Payment save(Payment payment);

    Optional<Payment> findByOrderId(UUID orderId);
}
