package com.tjvc.tv0724.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class MoneyAmount {
    @Getter
    private final BigDecimal amount;
    private final NumberFormat currencyFormat;

    public MoneyAmount(BigDecimal amount, Locale locale) {
        var currency = Currency.getInstance(locale);
        this.currencyFormat = NumberFormat.getCurrencyInstance(locale);
        this.currencyFormat.setCurrency(currency);

        var fractionDigits = currency.getDefaultFractionDigits();
        this.amount = amount.setScale(fractionDigits, RoundingMode.HALF_UP);
    }

    public String formatAmount() {
        return currencyFormat.format(amount);
    }
}
