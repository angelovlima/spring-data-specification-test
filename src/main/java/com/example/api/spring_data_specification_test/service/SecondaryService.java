package com.example.api.spring_data_specification_test.service;

import com.example.api.spring_data_specification_test.model.dto.SecondaryDTO;
import com.example.api.spring_data_specification_test.model.entity.SecondaryEntity;
import org.springframework.stereotype.Service;

@Service
public class SecondaryService {

    public SecondaryDTO toDTO(SecondaryEntity secondaryEntity) {
        return new SecondaryDTO(
                secondaryEntity.getId(),
                secondaryEntity.getName()
        );
    }

    public SecondaryEntity toEntity(SecondaryDTO secondaryDTO) {
        return new SecondaryEntity(
                secondaryDTO.getId(),
                secondaryDTO.getName()
        );
    }
}
