package com.tjvc.tv0724.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 Service for determining if a given date is a holiday or falls on the weekend
 */
@Service
public class DateService {
    // TODO separate impl from interface

    private final List<String> holidayDates;

    public DateService(@Value("${app.holiday.dates}") String holidayDatesString) {
        this.holidayDates = parseHolidayDates(holidayDatesString);
    }

    private List<String> parseHolidayDates(String holidayDatesString) {
        return Stream.of(holidayDatesString.split(","))
                .collect(Collectors.toList());
    }

    public boolean isHoliday(LocalDate date) {
        if (date == null) {
            return false;
        }
        return holidayDates.contains(date.format(DateTimeFormatter.ofPattern("MM-dd")))
                || isIndependenceDay(date) || isLaborDay(date); // Special holiday logic checks
    }

    private boolean isIndependenceDay(LocalDate date) {
        if (date == null) {
            return false;
        }
        var observedIndependenceDay = getObservedIndependenceDay(date.getYear());
        return date.equals(observedIndependenceDay);
    }
    
    private LocalDate getObservedIndependenceDay(int year) {
        var julyFourth = getJulyFourth(year);
        var dayOfWeek = julyFourth.getDayOfWeek();
        if (isSaturday(dayOfWeek)) {
            return julyFourth.minusDays(1);
        }
        if (isSunday(dayOfWeek)) {
            return julyFourth.plusDays(1);
        }
        return julyFourth;
    }
    
    private LocalDate getJulyFourth(int year) {
        return LocalDate.of(year, 7, 4);
    }

    private boolean isLaborDay(LocalDate date) {
        if (date == null) {
            return false;
        }
        var firstMondayInSeptember = getFirstMondayInSeptember(date.getYear());
        return date.equals(firstMondayInSeptember);
    }

    private LocalDate getFirstMondayInSeptember(int year) {
        var firstOfSeptember = LocalDate.of(year, 9, 1);
        var firstMonday = firstOfSeptember.with(DayOfWeek.MONDAY);
        if (firstOfSeptember.getDayOfWeek() != DayOfWeek.MONDAY) {
            firstMonday = firstMonday.plusWeeks(1);
        }
        return firstMonday;
    }

    public boolean isWeekend(LocalDate date) {
        if (date == null) {
            return false;
        }
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return isSaturday(dayOfWeek) || isSunday(dayOfWeek);
    }

    private static boolean isSunday(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SUNDAY;
    }

    private static boolean isSaturday(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY;
    }
}