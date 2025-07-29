package com.vehicles.VehicleTracker.model.dto;

import com.vehicles.VehicleTracker.model.enums.ViolationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViolationCountByTypeDto {
    private ViolationType violationType;
    private long count;
}