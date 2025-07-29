package com.vehicles.VehicleTracker.repository;

import com.vehicles.VehicleTracker.model.Violation;
import com.vehicles.VehicleTracker.model.dto.ViolationCountByTypeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ViolationRepository extends JpaRepository<Violation, Long> {
    List<Violation> findAllByTimeStampBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new com.vehicles.VehicleTracker.model.dto.ViolationCountByTypeDto(v.violationType, COUNT(v)) " +
            "FROM Violation v " +
            "WHERE v.timeStamp BETWEEN :start AND :end " +
            "GROUP BY v.violationType")
    List<ViolationCountByTypeDto> countViolationsByTypeInPeriod(LocalDateTime start, LocalDateTime end);
}
