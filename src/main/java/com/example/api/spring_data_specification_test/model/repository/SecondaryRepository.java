package com.example.api.spring_data_specification_test.model.repository;

import com.example.api.spring_data_specification_test.model.entity.SecondaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondaryRepository extends JpaRepository<SecondaryEntity, Long> {
}
