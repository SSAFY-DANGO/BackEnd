package com.dango.dango.domain.ingredientinformation.repository;

import com.dango.dango.domain.ingredientinformation.entity.IngredientInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.Optional;


public interface IngredientInformationRepository extends JpaRepository<IngredientInformation, Long> {
    Optional<IngredientInformation> findByName(String name);

    Page<IngredientInformation> findAllByNameContaining(String name, Pageable pageable);

    Page<IngredientInformation> findAllByTypeContaining(String type, Pageable pageable);

    Page<IngredientInformation> findAll(Pageable pageable);
}
