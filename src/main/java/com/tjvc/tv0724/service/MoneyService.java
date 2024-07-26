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

    public TotalAmountWithDiscount computeLineItemTotal(BigDecimal amount, int quantity, int discountPercentage) {
        var subtotal = amount.multiply(new BigDecimal(quantity));
        var subtotalMoneyAmount = new MoneyAmount(subtotal, locale);

        var discountAmountValue = subtotal.multiply(new BigDecimal(discountPercentage))
                .divide(new BigDecimal("100"), RoundingMode.HALF_UP);
        var discountMoneyAmount = new MoneyAmount(discountAmountValue, locale);

        return new TotalAmountWithDiscount(subtotalMoneyAmount, discountMoneyAmount, locale);
    }
}
