package com.food.ordering.system.payment.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.payment.service.domain.valueobject.CreditEntryId;

public class CreditEntry extends BaseEntity<CreditEntryId> {

    private final CustomerId customerId;
    private Money totalCreditAmount;

    private CreditEntry(Builder builder) {
        setId(builder.creditEntryId);
        this.customerId = builder.customerId;
        this.totalCreditAmount = builder.totalCreditAmount;
    }

    public void addCreditAmount(Money amout) {
        this.totalCreditAmount = this.totalCreditAmount.add(amout);
    }

    public void subtractCreditAmount(Money amout) {
        this.totalCreditAmount = this.totalCreditAmount.subtract(amout);
    }

    public static Builder builder() {
        return new Builder();
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public Money getTotalCreditAmount() {
        return totalCreditAmount;
    }

    public static class Builder {
        private CreditEntryId creditEntryId;
        private CustomerId customerId;
        private Money totalCreditAmount;

        public Builder() {
        }

        public Builder creditEntryId(CreditEntryId creditEntryId) {
            this.creditEntryId = creditEntryId;
            return this;
        }

        public Builder customerId(CustomerId customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder totalCreditAmount(Money totalCreditAmout) {
            this.totalCreditAmount = totalCreditAmout;
            return this;
        }

        public CreditEntry build() {
            return new CreditEntry(this);
        }
    }

}
