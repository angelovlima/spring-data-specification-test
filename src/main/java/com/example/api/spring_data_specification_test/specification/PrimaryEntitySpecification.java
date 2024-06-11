package com.example.api.spring_data_specification_test.specification;

import com.example.api.spring_data_specification_test.model.entity.PrimaryEntity;
import com.example.api.spring_data_specification_test.model.entity.SecondaryEntity;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

// Define uma classe que implementa a interface Specification do Spring Data JPA para a entidade PrimaryEntity.
// Esta classe é usada para criar critérios de busca dinâmicos.
public class PrimaryEntitySpecification implements Specification<PrimaryEntity> {

    // Neste exemplo, declara duas variáveis de instância para armazenar os critérios de filtro.
    // O valores vem por query params do Controller que recebe o Specification
    private Long id;
    private String secondaryEntityName;

    //Construtor que inicializa as variáveis de instância com os critérios de filtro fornecidos.
    public PrimaryEntitySpecification(Long id, String secondaryEntityName) {
        this.id = id;
        this.secondaryEntityName = secondaryEntityName;
    }

    //Cria uma lista de Predicate para armazenar os critérios de filtro.
    //No JPA, predicados são usados para criar consultas dinâmicas
    //Predicados seriam a representação do que é usado na cláusula WHERE do SQL
    @Override
    public Predicate toPredicate(Root<PrimaryEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (secondaryEntityName != null && !secondaryEntityName.isEmpty()) {
            // Faz um join entre PrimaryEntity e SecondaryEntity para acessar o campo name da entidade secundária.
            // Adiciona um condição para filtrar pelo nome da entidade secundária usando um LIKE com wildcards (%).
            Join<PrimaryEntity, SecondaryEntity> join = root.join("secondaryEntity");
            predicates.add(criteriaBuilder.like(join.get("name"), "%" + this.secondaryEntityName + "%"));
        }
        if (this.id != null) {
            // Adiciona uma condição para filtrar pelo id da entidade primária usando equal.
            predicates.add(criteriaBuilder.equal(root.get("id"), this.id));
        }
        //Converte a lista de predicados em um array e os combina usando o operador AND.
        //Retorna o predicado combinado.
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
