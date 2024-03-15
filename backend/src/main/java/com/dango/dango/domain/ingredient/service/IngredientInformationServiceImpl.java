package com.dango.dango.domain.ingredient.service;

import com.dango.dango.domain.ingredient.dto.IngredientInformationUpdateDTO;
import com.dango.dango.domain.ingredient.entity.IngredientInformation;
import com.dango.dango.domain.ingredient.exception.IngredientInformationNotFoundException;
import com.dango.dango.domain.ingredient.repository.IngredientInformationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientInformationServiceImpl implements IngredientInformationService {
    private final IngredientInformationRepository ingredientInformationRepository;

    @Override
    public IngredientInformation findIngredientInformationById(Long id) {
        // Optional null체크해서 유효하지 않으면 바로 던지기
        IngredientInformation ingredientInformation = ingredientInformationRepository
                .findById(id).orElseThrow(() -> new IngredientInformationNotFoundException("id " + id + " 없음"));
        return ingredientInformation;
    }

    @Override
    public IngredientInformation findIngredientInformationByName(String name) {
        // Optional null체크해서 유효하지 않으면 바로 던지기
        IngredientInformation ingredientInformation = ingredientInformationRepository.findByName(name)
                .orElseThrow(() -> new IngredientInformationNotFoundException("name " + name + " 없음"));
        return ingredientInformation;
    }

    @Override
    public IngredientInformation addIngredientInformation(IngredientInformation ingredientInformation) {
        // 구현체가 save() 트랜잭션 관리하고 있음
        return ingredientInformationRepository.save(ingredientInformation);
    }

    @Override
    public void deleteIngredientInformation(Long id) {
        ingredientInformationRepository.deleteById(id);
    }

    @Override
    public int updateIngredientInformation(Long id, IngredientInformationUpdateDTO ingredientInformationUpdateDTO) {
        return 0;
    }


}
