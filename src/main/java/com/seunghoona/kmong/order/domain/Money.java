package com.seunghoona.kmong.order.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class Money {

    public static final int INT_ZERO = 0;
    public static final Money ZERO = new Money(INT_ZERO);

    @Column(name = "amount")
    private long amount;

    public static Money of(long amount) {
        isAmountLessThan(amount);
        return new Money(amount);
    }

    private Money(long amount) {
        this.amount = amount;
    }

    private static void isAmountLessThan(long amount) {
        if (amount <= INT_ZERO) {
            throw new IllegalArgumentException("상품의 금액은 0보다 크거나 같아야합니다.");
        }
    }
}
