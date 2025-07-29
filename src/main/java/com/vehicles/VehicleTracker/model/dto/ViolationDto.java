package com.vehicles.VehicleTracker.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViolationDto {
    private String act;
    private String violationType;
    private LocalDateTime timeStamp;
    private String offenderName;
    private String vehiclePlate;
}
