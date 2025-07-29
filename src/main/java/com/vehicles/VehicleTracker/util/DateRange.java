package com.vehicles.VehicleTracker.util;

import java.time.LocalDateTime;

public class DateRange {
    private LocalDateTime start;
    private LocalDateTime end;

    public DateRange(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
