package com.vehicles.VehicleTracker.model;

import jakarta.persistence.CascadeType;
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
@Table(name = "person")
public class Person extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String egn;
    @Column(nullable = false)
    private String gender;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Ownership> ownerships;
    @OneToMany(mappedBy = "offender", cascade = CascadeType.ALL)
    private List<Violation> violations;
}
