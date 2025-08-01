package com.vehicles.VehicleTracker.repository;

import com.vehicles.VehicleTracker.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEgn(final String egn);
}
