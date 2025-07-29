package com.vehicles.VehicleTracker.service.impl;

import com.vehicles.VehicleTracker.model.Ownership;
import com.vehicles.VehicleTracker.model.Person;
import com.vehicles.VehicleTracker.repository.OwnershipRepository;
import com.vehicles.VehicleTracker.service.OwnershipService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnershipServiceImpl implements OwnershipService {
    private final OwnershipRepository ownershipRepository;

    public OwnershipServiceImpl(OwnershipRepository ownershipRepository) {
        this.ownershipRepository = ownershipRepository;
    }

    @Override
    public List<Person> findOwnersOfModelByColor(final String model, final String color) {
        return ownershipRepository
                .findByVehicleModelIgnoreCaseAndVehicleColorIgnoreCase(model, color)
                .stream()
                .map(Ownership::getPerson)
                .distinct()
                .toList();
    }
}
