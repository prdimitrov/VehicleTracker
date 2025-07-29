package com.vehicles.VehicleTracker.util;

import java.time.LocalDateTime;

public final class DateRangeUtil {
    public static LocalDateTime getYearStart(final int year) {
        return LocalDateTime.of(year, 1, 1, 0, 0);
    }

    public static LocalDateTime getYearEnd(final int year) {
        return LocalDateTime.of(year, 12, 31, 23, 59);
    }
}
