package com.tjvc.tv0724.service;

import com.tjvc.tv0724.model.MoneyAmount;
import com.tjvc.tv0724.model.TotalAmountWithDiscount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

@Service
public class MoneyService {
    private final Locale locale;

    public MoneyService(@Value("${app.locale:en-US}") String localeString) {
        this.locale = Locale.forLanguageTag(localeString.replace('_', '-')); // Make the locale uniform
    }

    public MoneyAmount createMoneyAmount(BigDecimal amount) {
        return new MoneyAmount(amount, locale);
    }

    public MoneyAmount computeLineItemTotal(BigDecimal amount, int quantity) {
        var subtotal = amount.multiply(new BigDecimal(quantity));
        return new MoneyAmount(subtotal, locale);
    }

    public TotalAmountWithDiscount applyDiscount(BigDecimal amount, int discountPercentage) {
        var discountAmountValue = amount.multiply(new BigDecimal(discountPercentage))
                .divide(new BigDecimal("100"), RoundingMode.HALF_UP);
        var discountMoneyAmount = new MoneyAmount(discountAmountValue, locale);

        return new TotalAmountWithDiscount(new MoneyAmount(amount, locale), discountMoneyAmount, locale);
    }
}
