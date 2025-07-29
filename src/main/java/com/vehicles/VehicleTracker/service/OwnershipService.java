package com.vehicles.VehicleTracker.service;

import com.vehicles.VehicleTracker.model.Person;

import java.util.List;

public interface OwnershipService {
    List<Person> findOwnersOfModelByColor(final String model, final String color);
}
