package com.dango.dango.domain.ingredient.repository;

import com.dango.dango.domain.ingredient.entity.IngredientInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IngredientInformationRepository extends JpaRepository<IngredientInformation, Long> {
    Optional<IngredientInformation> findByName(String name);
    List<IngredientInformation> findAllByName(String name);
}
