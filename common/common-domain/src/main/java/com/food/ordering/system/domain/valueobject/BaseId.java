package com.food.ordering.system.domain.valueobject;

import java.util.Objects;

public abstract class BaseId<IdType> {
    private final IdType value;

    public BaseId(IdType value) {
        this.value = value;
    }

    public IdType getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseId<?> baseId = (BaseId<?>) o;
        return Objects.equals(value, baseId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
