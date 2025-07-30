package com.vehicles.VehicleTracker.init;

import com.vehicles.VehicleTracker.model.Ownership;
import com.vehicles.VehicleTracker.model.Person;
import com.vehicles.VehicleTracker.model.Vehicle;
import com.vehicles.VehicleTracker.model.Violation;
import com.vehicles.VehicleTracker.model.enums.ViolationType;
import com.vehicles.VehicleTracker.repository.OwnershipRepository;
import com.vehicles.VehicleTracker.repository.PersonRepository;
import com.vehicles.VehicleTracker.repository.VehicleRepository;
import com.vehicles.VehicleTracker.repository.ViolationRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class InitializeTestData implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final VehicleRepository vehicleRepository;
    private final OwnershipRepository ownershipRepository;
    private final ViolationRepository violationRepository;
    private static final Random random = new Random();

    @Override
    public void run(String... args) {
        Person person = new Person();
        person.setName("Petar Dimitrov");
        person.setAddress("Targovishte, Bulgaria");
        person.setEgn("5112072341");
        person.setGender("male");

        Person secondPerson = new Person();
        secondPerson.setName("Edin Gospodin");
        secondPerson.setAddress("Targovishte, Bulgaria");
        secondPerson.setEgn("9712102345");
        secondPerson.setGender("male");
        personRepository.saveAll(List.of(person, secondPerson));

        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNumber("T9952AK");
        vehicle.setModel("ford");
        vehicle.setColor("сив");
        vehicle.setEngineCode("QJBB");

        Vehicle secondVehicle = new Vehicle();
        secondVehicle.setPlateNumber("T0641CT");
        secondVehicle.setModel("ferrari");
        secondVehicle.setColor("червен");
        secondVehicle.setEngineCode("DW10TD");
        vehicleRepository.saveAll(List.of(vehicle, secondVehicle));

        Ownership ownership = new Ownership();
        ownership.setPerson(person);
        ownership.setVehicle(vehicle);
        ownership.setAcquisitionDate(LocalDateTime.of(2001, 12, 2, 14, 10));
        ownership.setTerminationDate(ownership.getAcquisitionDate().plusYears(1 + random.nextInt(20)));

        Ownership secondOwnership = new Ownership();
        secondOwnership.setPerson(person);
        secondOwnership.setVehicle(secondVehicle);
        secondOwnership.setAcquisitionDate(LocalDateTime.of(2001, 5, 3, 15, 25));
        secondOwnership.setTerminationDate(ownership.getAcquisitionDate().plusYears(1 + random.nextInt(20)));
        ownershipRepository.saveAll(List.of(ownership, secondOwnership));

        Ownership thirdOwnership = new Ownership();
        thirdOwnership.setPerson(secondPerson);
        thirdOwnership.setVehicle(secondVehicle);
        thirdOwnership.setAcquisitionDate(LocalDateTime.of(2019, 12, 2, 0, 0));
        thirdOwnership.setTerminationDate(ownership.getAcquisitionDate().plusYears(1 + random.nextInt(20)));
        ownershipRepository.saveAll(List.of(ownership, secondOwnership, thirdOwnership));

        Violation firstViolation = new Violation();
        firstViolation.setAct("ACT91372");
        firstViolation.setViolationType(ViolationType.SPEEDING);
        firstViolation.setTimeStamp(LocalDateTime.of(2002, 5, 10, 14, 3,
                23));
        firstViolation.setOffender(person);
        firstViolation.setVehicle(vehicle);

        Violation secondViolation = new Violation();
        secondViolation.setAct("ACT92372");
        secondViolation.setViolationType(ViolationType.NO_LICENSE);
        secondViolation.setTimeStamp(LocalDateTime.of(2002, 3, 21, 11, 25));
        secondViolation.setOffender(person);
        secondViolation.setVehicle(vehicle);

        Violation thirdViolation = new Violation();
        thirdViolation.setAct("ACT93372");
        thirdViolation.setViolationType(ViolationType.NO_LICENSE);
        thirdViolation.setTimeStamp(LocalDateTime.of(2023, 5, 21, 16, 13));
        thirdViolation.setOffender(person);
        thirdViolation.setVehicle(vehicle);

        Violation fourthViolation = new Violation();
        fourthViolation.setAct("ACT94372");
        fourthViolation.setViolationType(ViolationType.NO_LICENSE);
        fourthViolation.setTimeStamp(LocalDateTime.of(2002, 5, 21, 16, 13));
        fourthViolation.setOffender(person);
        fourthViolation.setVehicle(vehicle);

        violationRepository.saveAll(List.of(firstViolation, secondViolation, thirdViolation, fourthViolation));
    }
}
