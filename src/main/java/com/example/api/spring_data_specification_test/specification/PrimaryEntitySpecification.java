package com.example.api.spring_data_specification_test.specification;

import com.example.api.spring_data_specification_test.model.entity.PrimaryEntity;
import com.example.api.spring_data_specification_test.model.entity.SecondaryEntity;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PrimaryEntitySpecification implements Specification<PrimaryEntity> {

    private Long id;
    private String secondaryEntityName;

    public PrimaryEntitySpecification(Long id, String secondaryEntityName) {
        this.id = id;
        this.secondaryEntityName = secondaryEntityName;
    }

    @Override
    public Predicate toPredicate(Root<PrimaryEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (secondaryEntityName != null && !secondaryEntityName.isEmpty()) {
            Join<PrimaryEntity, SecondaryEntity> join = root.join("secondaryEntity");
            predicates.add(criteriaBuilder.like(join.get("name"), "%" + this.secondaryEntityName + "%"));
        }
        if (this.id != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), this.id));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
