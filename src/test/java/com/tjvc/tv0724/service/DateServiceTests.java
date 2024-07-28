package com.tjvc.tv0724.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateServiceTests {

    private final DateService dateService = new DateService("06-19,12-25");

    @Test
    public void juneteenthIsHoliday() {
        assertTrue(dateService.isHoliday(LocalDate.of(2024, 6, 19)));
    }

    @Test
    public void independenceDayOnWeekdayIsHoliday() {
        assertTrue(dateService.isHoliday(LocalDate.of(2024, 7, 4)));
    }

    @Test
    public void independenceDayOnSundayIsNotHoliday() {
        assertFalse(dateService.isHoliday(LocalDate.of(2021, 7, 4)));
    }

    @Test
    public void independenceDayIsObservedOnMonday() {
        assertTrue(dateService.isHoliday(LocalDate.of(2021, 7, 5)));
    }

    @Test
    public void independenceDayOnSaturdayIsNotHoliday() {
        assertFalse(dateService.isHoliday(LocalDate.of(2015, 7, 4)));
    }

    @Test
    public void independenceDayIsObservedOnFriday() {
        assertTrue(dateService.isHoliday(LocalDate.of(2015, 7, 3)));
    }

    @Test
    public void isWeekend1() {
        assertTrue(dateService.isWeekend(LocalDate.of(2015, 7, 4)));
    }

    @Test
    public void isWeekend2() {
        assertTrue(dateService.isWeekend(LocalDate.of(2024, 7, 21)));
    }

    @Test
    public void isWeekend3() {
        assertTrue(dateService.isWeekend(LocalDate.of(2023, 3, 18)));
    }

    @Test
    public void isNotWeekend1() {
        assertFalse(dateService.isWeekend(LocalDate.of(2015, 7, 6)));
    }

    @Test
    public void isNotWeekend2() {
        assertFalse(dateService.isWeekend(LocalDate.of(2024, 7, 22)));
    }

    @Test
    public void isNotWeekend3() {
        assertFalse(dateService.isWeekend(LocalDate.of(2023, 3, 17)));
    }
}