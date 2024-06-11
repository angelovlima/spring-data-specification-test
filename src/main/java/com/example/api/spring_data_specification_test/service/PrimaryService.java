package com.example.api.spring_data_specification_test.service;

import com.example.api.spring_data_specification_test.model.dto.PrimaryDTO;
import com.example.api.spring_data_specification_test.model.dto.SecondaryDTO;
import com.example.api.spring_data_specification_test.model.entity.PrimaryEntity;
import com.example.api.spring_data_specification_test.model.repository.PrimaryRepository;
import com.example.api.spring_data_specification_test.specification.PrimaryEntitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrimaryService {

    private final PrimaryRepository primaryRepository;
    private final SecondaryService secondaryService;

    @Autowired
    public PrimaryService(
            PrimaryRepository primaryRepository,
            SecondaryService secondaryService
    ) {
        this.primaryRepository = primaryRepository;
        this.secondaryService = secondaryService;
    }

    public PrimaryDTO toDTO(PrimaryEntity primaryEntity) {
        SecondaryDTO secondaryDTO = secondaryService.toDTO(primaryEntity.getSecondaryEntity());
        return new PrimaryDTO(
            primaryEntity.getId(),
            secondaryDTO,
            primaryEntity.getLongField()
        );
    }

    public List<PrimaryDTO> findListFiltered(PrimaryEntitySpecification specification) {
        // Busca entidades paginadas e filtradas e as converte em DTOs.
        return primaryRepository.findAll(specification).stream().map(this::toDTO).toList();
    }

    public Page<PrimaryDTO> findListFilteredAndPageable(PrimaryEntitySpecification specification, Pageable pageable) {
        // Busca entidades filtradas e as converte em DTOs.
        return primaryRepository.findAll(specification, pageable).map(this::toDTO);
    }

    public Page<PrimaryDTO> findAllPageable(Pageable pageable) {
        // Busca todas as entidades paginadas e as converte em DTOs.
        return primaryRepository.findAll(pageable).map(this::toDTO);
    }

}
