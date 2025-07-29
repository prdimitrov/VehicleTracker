package com.vehicles.VehicleTracker.service.impl;

import com.vehicles.VehicleTracker.model.Violation;
import com.vehicles.VehicleTracker.model.dto.ViolationCountByTypeDto;
import com.vehicles.VehicleTracker.repository.ViolationRepository;
import com.vehicles.VehicleTracker.service.ViolationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ViolationServiceImpl implements ViolationService {
    private final ViolationRepository violationRepository;

    public ViolationServiceImpl(ViolationRepository violationRepository) {
        this.violationRepository = violationRepository;
    }

    @Override
    public List<Violation> getViolationsInYear(final int year) {
        LocalDateTime start = LocalDateTime.of(year, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, 12, 31, 23, 59);
        return violationRepository.findAllByTimeStampBetween(start, end);
    }

    @Override
    public List<ViolationCountByTypeDto> getViolationStatsForYear(int year) {
        LocalDateTime start = LocalDateTime.of(year, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, 12, 31, 23, 59);
        return violationRepository.countViolationsByTypeInPeriod(start, end);
    }
}
