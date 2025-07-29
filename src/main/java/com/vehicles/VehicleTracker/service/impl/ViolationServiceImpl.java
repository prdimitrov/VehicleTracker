package com.vehicles.VehicleTracker.service.impl;

import com.vehicles.VehicleTracker.model.Violation;
import com.vehicles.VehicleTracker.model.dto.ViolationCountByTypeDto;
import com.vehicles.VehicleTracker.model.dto.ViolationDto;
import com.vehicles.VehicleTracker.repository.ViolationRepository;
import com.vehicles.VehicleTracker.service.ViolationService;
import com.vehicles.VehicleTracker.util.DateRangeUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ViolationServiceImpl implements ViolationService {
    private final ViolationRepository violationRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ViolationDto> getViolationsInYear(final int year) {
        return violationRepository.findAllByTimeStampBetween(
                        DateRangeUtil.getYearStart(year),
                        DateRangeUtil.getYearEnd(year)
                ).stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public List<ViolationCountByTypeDto> getViolationStatsForYear(final int year) {
        return violationRepository.countViolationsByTypeInPeriod(
                DateRangeUtil.getYearStart(year),
                DateRangeUtil.getYearEnd(year));
    }

    private ViolationDto convertToDto(final Violation violation) {
        ViolationDto dto = modelMapper.map(violation, ViolationDto.class);
        dto.setOffenderName(violation.getOffender().getName());
        dto.setVehiclePlate(violation.getVehicle().getPlateNumber());
        return dto;
    }
}