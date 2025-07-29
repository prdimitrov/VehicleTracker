package com.vehicles.VehicleTracker.service.impl;

import com.vehicles.VehicleTracker.model.Ownership;
import com.vehicles.VehicleTracker.model.dto.PersonDto;
import com.vehicles.VehicleTracker.repository.OwnershipRepository;
import com.vehicles.VehicleTracker.service.OwnershipService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OwnershipServiceImpl implements OwnershipService {
    private final OwnershipRepository ownershipRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PersonDto> findOwnersByModelAndColor(final String model,
                                                     final String color) {
        return ownershipRepository
                .findByVehicleModelIgnoreCaseAndVehicleColorIgnoreCase(model, color)
                .stream()
                .map(Ownership::getPerson)
                .distinct()
                .map(person -> modelMapper.map(person, PersonDto.class))
                .toList();
    }
}
