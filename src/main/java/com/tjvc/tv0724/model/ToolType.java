package com.tjvc.tv0724.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum ToolType {
    Chainsaw("1.49", true, false, true),
    Ladder("1.99", true, true, false),
    Jackhammer("2.99", true, false, false);

    private final BigDecimal dailyCharge;
    private final boolean chargeOnWeekday;
    private final boolean chargeOnWeekend;
    private final boolean chargeOnHoliday;

    ToolType(String dailyCharge, boolean chargeOnWeekday, boolean chargeOnWeekend, boolean chargeOnHoliday) {
        this.dailyCharge = new BigDecimal(dailyCharge);

        this.chargeOnWeekday = chargeOnWeekday;
        this.chargeOnWeekend = chargeOnWeekend;
        this.chargeOnHoliday = chargeOnHoliday;
    }
}
