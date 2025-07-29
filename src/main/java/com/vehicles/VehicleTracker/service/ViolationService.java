package com.vehicles.VehicleTracker.service;

import com.vehicles.VehicleTracker.model.Violation;
import com.vehicles.VehicleTracker.model.dto.ViolationCountByTypeDto;

import java.util.List;

public interface ViolationService {
    List<Violation> getViolationsInYear(final int year);
    List<ViolationCountByTypeDto> getViolationStatsForYear(int year);
}
