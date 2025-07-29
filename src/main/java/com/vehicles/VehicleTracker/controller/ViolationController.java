package com.vehicles.VehicleTracker.controller;

import com.vehicles.VehicleTracker.service.OwnershipService;
import com.vehicles.VehicleTracker.service.PersonService;
import com.vehicles.VehicleTracker.service.ViolationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ViolationController {

    private final ViolationService violationService;
    private final OwnershipService ownershipService;
    private final PersonService personService;

    public ViolationController(ViolationService violationService,
                               OwnershipService ownershipService,
                               PersonService personService) {
        this.violationService = violationService;
        this.ownershipService = ownershipService;
        this.personService = personService;
    }

    @GetMapping("/violations")
    public ResponseEntity<?> getViolationsByYear(@RequestParam(name = "year") final int year) {
        return ResponseEntity.ok(violationService.getViolationsInYear(year));
    }

    @GetMapping("/owners")
    public ResponseEntity<?> getOwnersOfModelByColor(@RequestParam(name = "model") final String model,
                                                     @RequestParam(name = "color") final String color) {
        return ResponseEntity.ok(ownershipService.findOwnersOfModelByColor(model, color));
    }

    @GetMapping("/violations/stats")
    public ResponseEntity<?> getViolationStatsByYear(@RequestParam(name = "year") final int year) {
        return ResponseEntity.ok(violationService.getViolationStatsForYear(year));
    }

    @DeleteMapping("/persons")
    public ResponseEntity<String> deletePersonByEgn(@RequestParam(name = "egn") final String egn) {
        return ResponseEntity.ok(personService.deletePersonByEgn(egn));
    }
}