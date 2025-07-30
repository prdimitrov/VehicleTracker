package com.vehicles.VehicleTracker.controller;

import com.vehicles.VehicleTracker.model.dto.PersonDto;
import com.vehicles.VehicleTracker.model.dto.ViolationCountByTypeDto;
import com.vehicles.VehicleTracker.model.dto.ViolationDto;
import com.vehicles.VehicleTracker.service.OwnershipService;
import com.vehicles.VehicleTracker.service.PersonService;
import com.vehicles.VehicleTracker.service.ViolationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ViolationController {

    private static final String YEAR = "year";
    private static final String MODEL = "model";
    private static final String COLOR = "color";
    private static final String EGN = "egn";
    private final ViolationService violationService;
    private final OwnershipService ownershipService;
    private final PersonService personService;

    @GetMapping("/violations")
    public ResponseEntity<List<ViolationDto>> getViolationsByYear(@RequestParam(name = YEAR) final int year) {
        return ResponseEntity.ok(violationService.getViolationsInYear(year));
    }

    @GetMapping("/owners")
    public ResponseEntity<List<PersonDto>> getOwnersByModelAndColor(@RequestParam(name = MODEL) final String model,
                                                                   @RequestParam(name = COLOR) final String color) {
        return ResponseEntity.ok(ownershipService.findOwnersByModelAndColor(model, color));
    }

    @GetMapping("/violations/stats")
    public ResponseEntity<List<ViolationCountByTypeDto>> getViolationStatsByYear(@RequestParam(name = YEAR) final int year) {
        return ResponseEntity.ok(violationService.getViolationStatsForYear(year));
    }

    @DeleteMapping("/persons")
    public ResponseEntity<String> deletePersonByEgn(@RequestParam(name = EGN) final String egn) {
        return ResponseEntity.ok(personService.deletePersonByEgn(egn));
    }
}