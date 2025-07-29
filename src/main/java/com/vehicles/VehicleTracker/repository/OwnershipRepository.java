package com.vehicles.VehicleTracker.repository;

import com.vehicles.VehicleTracker.model.Ownership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnershipRepository extends JpaRepository<Ownership, Long> {
    List<Ownership> findByVehicleModelIgnoreCaseAndVehicleColorIgnoreCase(String model, String color);
}
