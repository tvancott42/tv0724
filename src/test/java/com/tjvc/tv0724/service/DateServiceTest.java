package com.tjvc.tv0724.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateServiceTest {

    private final DateService dateService = new DateService("06-19,12-25");

    @Test
    void juneteenthIsHoliday() {
        assertTrue(dateService.isHoliday(LocalDate.of(2024, 6, 19)));
    }

    @Test
    void independenceDayOnWeekdayIsHoliday() {
        assertTrue(dateService.isHoliday(LocalDate.of(2024, 7, 4)));
    }

    @Test
    void independenceDayOnSundayIsNotHoliday() {
        assertFalse(dateService.isHoliday(LocalDate.of(2021, 7, 4)));
    }

    @Test
    void independenceDayIsObservedOnMonday() {
        assertTrue(dateService.isHoliday(LocalDate.of(2021, 7, 5)));
    }

    @Test
    void independenceDayOnSaturdayIsNotHoliday() {
        assertFalse(dateService.isHoliday(LocalDate.of(2015, 7, 4)));
    }

    @Test
    void independenceDayIsObservedOnFriday() {
        assertTrue(dateService.isHoliday(LocalDate.of(2015, 7, 3)));
    }

    @Test
    void isWeekend1() {
        assertTrue(dateService.isWeekend(LocalDate.of(2015, 7, 4)));
    }

    @Test
    void isWeekend2() {
        assertTrue(dateService.isWeekend(LocalDate.of(2024, 7, 21)));
    }

    @Test
    void isWeekend3() {
        assertTrue(dateService.isWeekend(LocalDate.of(2023, 3, 18)));
    }

    @Test
    void isNotWeekend1() {
        assertFalse(dateService.isWeekend(LocalDate.of(2015, 7, 6)));
    }

    @Test
    void isNotWeekend2() {
        assertFalse(dateService.isWeekend(LocalDate.of(2024, 7, 22)));
    }

    @Test
    void isNotWeekend3() {
        assertFalse(dateService.isWeekend(LocalDate.of(2023, 3, 17)));
    }
}