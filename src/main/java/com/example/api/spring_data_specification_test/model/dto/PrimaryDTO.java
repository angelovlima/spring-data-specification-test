package com.example.api.spring_data_specification_test.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrimaryDTO {
    private Long id;
    private SecondaryDTO secondaryDTO;
    private Long longField;
}
