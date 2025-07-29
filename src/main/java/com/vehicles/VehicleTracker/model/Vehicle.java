package com.vehicles.VehicleTracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "vehicle")
public class Vehicle extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String plateNumber;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String color;
    @Column(unique = true, nullable = false)
    private String engineCode;
    @OneToMany(mappedBy = "vehicle")
    private List<Ownership> ownerships;
    @OneToMany(mappedBy = "vehicle")
    private List<Violation> violations;
}