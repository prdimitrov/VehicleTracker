package com.vehicles.VehicleTracker.service;

import com.vehicles.VehicleTracker.model.dto.ViolationCountByTypeDto;
import com.vehicles.VehicleTracker.model.dto.ViolationDto;

import java.util.List;

public interface ViolationService {
    List<ViolationDto> getViolationsInYear(final int year);

    List<ViolationCountByTypeDto> getViolationStatsForYear(final int year);
}