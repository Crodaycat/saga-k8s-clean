package com.food.ordering.system.domain.event.publisher;

import com.food.ordering.system.domain.event.DomainEvent;

public interface DomainEventPublisher<EventType extends DomainEvent> {
    void publish(EventType domainEvent);
}
