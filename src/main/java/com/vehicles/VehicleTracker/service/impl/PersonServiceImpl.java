package com.vehicles.VehicleTracker.service.impl;

import com.vehicles.VehicleTracker.model.Person;
import com.vehicles.VehicleTracker.repository.OwnershipRepository;
import com.vehicles.VehicleTracker.repository.PersonRepository;
import com.vehicles.VehicleTracker.repository.ViolationRepository;
import com.vehicles.VehicleTracker.service.PersonService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private static final String SUCCESSFULLY_DELETED_PERSON_WITH_EGN = "Successfully deleted Person with EGN {}!";
    private static final String PERSON_WITH_EGN_NOT_FOUND = "Person with EGN: {} not found!";
    private static final String TARGET = "{}";
    private final PersonRepository personRepository;

    @Override
    @Transactional
    public String deletePersonByEgn(final String egn) {
        Person person = personRepository.findByEgn(egn)
                .orElseThrow(() -> new IllegalArgumentException(PERSON_WITH_EGN_NOT_FOUND.replace(TARGET, egn)));
        personRepository.delete(person);
        return SUCCESSFULLY_DELETED_PERSON_WITH_EGN.replace(TARGET, egn);
    }
}
