package com.tjvc.tv0724.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record RentalAgreement(String toolCode, ToolType toolType, String toolBrand, int rentalDays,
                              LocalDate checkOutDate, LocalDate dueDate, MoneyAmount toolDailyCharge, int chargeableDays,
                              int discountPercent, TotalAmountWithDiscount totalWithDiscount) {

    public void printRentalAgreementReport() {
        System.out.println("--- Rental Agreement ---");
        System.out.println("Tool code: " + toolCode());
        System.out.println("Tool type: " + toolType());
        System.out.println("Tool brand: " + toolBrand());
        System.out.println("Rental days: " + rentalDays());
        System.out.println("Check-out date: " + formatDate(checkOutDate()));
        System.out.println("Due date: " + formatDate(dueDate()));
        System.out.println("Daily charge: " + toolDailyCharge.formatAmount());
        System.out.println("Chargeable days: " + chargeableDays());
        System.out.println("Pre-discount charge: " + totalWithDiscount.getAmountBeforeDiscount().formatAmount());
        System.out.println("Discount percent: " + discountPercent() + "%");
        System.out.println("Discount amount: " + totalWithDiscount.getDiscountAmount().formatAmount());
        System.out.println("Final charge: " + totalWithDiscount.formatAmount());
    }

    private static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MM/dd/yy"));
    }
}
