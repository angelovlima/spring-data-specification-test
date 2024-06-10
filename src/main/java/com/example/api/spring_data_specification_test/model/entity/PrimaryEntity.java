package com.example.api.spring_data_specification_test.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "primary_entity")
public class PrimaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_primary_entity")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_secondary_entity", nullable = false)
    private SecondaryEntity secondaryEntity;

    @Column(name = "long_field", nullable = false)
    private Long longField;
}
