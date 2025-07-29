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
        personRepository.save(person);

        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNumber("T9952AK");
        vehicle.setModel("ferrari");
        vehicle.setColor("червен");
        vehicle.setEngineCode("QJBB");
        vehicleRepository.save(vehicle);

        Ownership ownership = new Ownership();
        ownership.setPerson(person);
        ownership.setVehicle(vehicle);
        ownership.setAcquisitionDate(LocalDateTime.of(2019, 12, 2, 0, 0));
        ownership.setTerminationDate(ownership.getAcquisitionDate().plusMonths(1 + random.nextInt(12)));

        ownershipRepository.save(ownership);

        Violation violation1 = new Violation();
        violation1.setAct("T9234");
        violation1.setViolationType(ViolationType.SPEEDING);
        violation1.setTimeStamp(LocalDateTime.of(2024, 5, 10, 14, 0));
        violation1.setOffender(person);
        violation1.setVehicle(vehicle);

        Violation violation2 = new Violation();
        violation2.setAct("ACT456");
        violation2.setViolationType(ViolationType.NO_LICENSE);
        violation2.setTimeStamp(LocalDateTime.of(2024, 7, 21, 11, 30));
        violation2.setOffender(person);
        violation2.setVehicle(vehicle);

        violationRepository.saveAll(List.of(violation1, violation2));
    }
}
