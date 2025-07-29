package com.vehicles.VehicleTracker.model;

import com.vehicles.VehicleTracker.model.enums.ViolationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "violation")
public class Violation extends BaseEntity {
    private String act;
    @Enumerated(EnumType.STRING)
    private ViolationType violationType;
    @Column(nullable = false)
    private LocalDateTime timeStamp;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person offender;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}