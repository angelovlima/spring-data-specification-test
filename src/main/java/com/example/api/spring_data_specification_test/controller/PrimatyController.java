package com.example.api.spring_data_specification_test.controller;

import com.example.api.spring_data_specification_test.model.dto.PrimaryDTO;
import com.example.api.spring_data_specification_test.model.entity.PrimaryEntity;
import com.example.api.spring_data_specification_test.service.PrimaryService;
import com.example.api.spring_data_specification_test.specification.PrimaryEntitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/primary")
public class PrimatyController {

    private final PrimaryService primaryService;

    @Autowired
    public PrimatyController(PrimaryService primaryService) {
        this.primaryService = primaryService;
    }

    @GetMapping("/find-with-filters")
    public ResponseEntity<List<PrimaryDTO>> findAllFiltered(PrimaryEntitySpecification specification) {
        // Chama o serviço para buscar dados filtrados sem paginação
        List<PrimaryDTO> listPrimaryDTO = primaryService.findListFiltered(specification);
        // Retorna os dados filtrados com status HTTP 200 (OK).
        return ResponseEntity.ok(listPrimaryDTO);
    }

    @GetMapping("/find-with-filters-pageable")
    public ResponseEntity<Page<PrimaryDTO>> findAllFilteredAndPageable(PrimaryEntitySpecification specification, Pageable pageable) {
        // Chama o serviço para buscar dados filtrados com paginação.
        Page<PrimaryDTO> pagePrimaryDTO = primaryService.findListFilteredAndPageable(specification, pageable);
        return ResponseEntity.ok(pagePrimaryDTO);
    }

    @GetMapping("/find-pageable")
    public ResponseEntity<Page<PrimaryDTO>> findAllPageable(Pageable pageable) {
        // Chama o serviço para buscar todos os dados com paginação.
        Page<PrimaryDTO> pagePrimaryDTO = primaryService.findAllPageable(pageable);
        return ResponseEntity.ok(pagePrimaryDTO);
    }

}
