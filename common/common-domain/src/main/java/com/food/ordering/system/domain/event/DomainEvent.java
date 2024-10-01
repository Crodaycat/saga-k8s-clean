package com.food.ordering.system.domain.event;

public interface DomainEvent<EventType> {
    void fire();
}
