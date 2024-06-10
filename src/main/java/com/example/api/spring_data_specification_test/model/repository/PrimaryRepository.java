package com.example.api.spring_data_specification_test.model.repository;

import com.example.api.spring_data_specification_test.model.entity.PrimaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrimaryRepository extends JpaRepository<PrimaryEntity, Long>, JpaSpecificationExecutor<PrimaryEntity> {
}
