package com.vehicles.VehicleTracker.service;

import com.vehicles.VehicleTracker.model.dto.PersonDto;

import java.util.List;

public interface OwnershipService {
    List<PersonDto> findOwnersByModelAndColor(final String model,
                                              final String color);
}
