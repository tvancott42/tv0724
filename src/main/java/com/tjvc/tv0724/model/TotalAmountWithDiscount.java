package com.tjvc.tv0724.model;

import lombok.Getter;

import java.util.Locale;

@Getter
public class TotalAmountWithDiscount extends MoneyAmount {
    private final MoneyAmount amountBeforeDiscount;
    private final MoneyAmount discountAmount;

    public TotalAmountWithDiscount(MoneyAmount amountBeforeDiscount, MoneyAmount discountAmount, Locale locale) {
        super(amountBeforeDiscount.getAmount().subtract(discountAmount.getAmount()), locale);
        this.amountBeforeDiscount = amountBeforeDiscount;
        this.discountAmount = discountAmount;
    }
}
