package com.dango.dango.domain.ingredientinformation.repository;

import com.dango.dango.domain.ingredientinformation.entity.IngredientInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientInformationRepository extends JpaRepository<IngredientInformation, Long> {
    Optional<IngredientInformation> findByName(String name);

    List<IngredientInformation> findAllByNameContaining(String name);

    List<IngredientInformation> findAllByTypeContaining(String type);
}
