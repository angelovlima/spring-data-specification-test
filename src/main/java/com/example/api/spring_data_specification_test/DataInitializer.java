package com.example.api.spring_data_specification_test;

import com.example.api.spring_data_specification_test.model.entity.PrimaryEntity;
import com.example.api.spring_data_specification_test.model.entity.SecondaryEntity;
import com.example.api.spring_data_specification_test.model.repository.PrimaryRepository;
import com.example.api.spring_data_specification_test.model.repository.SecondaryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeDatabase(SecondaryRepository secondaryRepository, PrimaryRepository primaryRepository) {
        return args -> {
            SecondaryEntity secondaryEntity = new SecondaryEntity();
            secondaryEntity.setName("Secondary entity");
            PrimaryEntity primaryEntity = new PrimaryEntity();
            primaryEntity.setLongField(1L);
            primaryEntity.setSecondaryEntity(secondaryRepository.save(secondaryEntity));
            primaryRepository.save(primaryEntity);

            SecondaryEntity otherSecondaryEntity = new SecondaryEntity();
            otherSecondaryEntity.setName("Other");
            PrimaryEntity otherPrimaryEntity = new PrimaryEntity();
            otherPrimaryEntity.setLongField(2L);
            otherPrimaryEntity.setSecondaryEntity(secondaryRepository.save(otherSecondaryEntity));
            primaryRepository.save(otherPrimaryEntity);
        };
    }
}