package com.food.ordering.system.service.domain.dto.message;

import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import com.food.ordering.system.domain.valueobject.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantApprovalResponse {
    private String id;
    private String sagaId;
    private String orderId;
    private String restaurantId;
    private Instant createdAt;
    private PaymentStatus paymentStatus;
    private OrderApprovalStatus orOderApprovalStatus;
    private List<String> failureMessages;
}
