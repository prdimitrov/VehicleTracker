package com.vehicles.VehicleTracker.service.impl;

import com.vehicles.VehicleTracker.model.Person;
import com.vehicles.VehicleTracker.repository.OwnershipRepository;
import com.vehicles.VehicleTracker.repository.PersonRepository;
import com.vehicles.VehicleTracker.repository.ViolationRepository;
import com.vehicles.VehicleTracker.service.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    private static final String SUCCESSFULLY_DELETED_PERSON_WITH_EGN = "Successfully deleted Person with EGN {}!";
    private static final String PERSON_WITH_EGN_NOT_FOUND = "Person with EGN: {} not found!";
    private static final String TARGET = "{}";
    private final PersonRepository personRepository;
    private final ViolationRepository violationRepository;
    private final OwnershipRepository ownershipRepository;

    public PersonServiceImpl(PersonRepository personRepository,
                             ViolationRepository violationRepository,
                             OwnershipRepository ownershipRepository) {
        this.personRepository = personRepository;
        this.violationRepository = violationRepository;
        this.ownershipRepository = ownershipRepository;
    }

    @Override
    @Transactional
    public String deletePersonByEgn(String egn) {
        Person person = personRepository.findByEgn(egn)
                .orElseThrow(() -> new IllegalArgumentException(PERSON_WITH_EGN_NOT_FOUND.replace(TARGET, egn)));
        violationRepository.deleteAll(person.getViolations());
        ownershipRepository.deleteAll(person.getOwnerships());
        personRepository.delete(person);

        return SUCCESSFULLY_DELETED_PERSON_WITH_EGN.replace(TARGET, egn);
    }
}
