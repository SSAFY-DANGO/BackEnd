package com.dango.dango.domain.ingredient.service;

import com.dango.dango.domain.ingredient.dto.IngredientInformationUpdateDTO;
import com.dango.dango.domain.ingredient.entity.IngredientInformation;
import com.dango.dango.domain.ingredient.exception.IngredientInformationNotFoundException;
import com.dango.dango.domain.ingredient.repository.IngredientInformationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Optional;

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
    @Transactional
    public int updateIngredientInformation(Long id, IngredientInformationUpdateDTO ingredientInformationUpdateDTO) {
        IngredientInformation ingredientInformation = findIngredientInformationById(id);

        Class<?> dtoClass = ingredientInformationUpdateDTO.getClass();
        Field[] fields = dtoClass.getDeclaredFields();

        for(Field field : fields ) {
            try {
                // 필드값이 null이 아니면
                Object value = field.get(ingredientInformationUpdateDTO);
                if (value != null) {
                    // dto 클래스의 필드이름이랑 같은 필드 타입 찾기
                    Field entryField = IngredientInformation.class.getDeclaredField(field.getName());
                    // reflection 접근 가능하게 수정하기
                    entryField.setAccessible(true);
                    entryField.set(ingredientInformation, value);
                }
            } catch (Throwable e) {
                // 필드가 없거나 접근 불가능한 경우?
                e.printStackTrace();
            }

        }
        return 1;
        // DTO 의 값이 null이 아닌경우 setter로 모두 업데이트
//
//        String name = ingredientInformationUpdateDTO.getName();
//        if (name != null) {
//            ingredientInformation.setName(name);
//        }
//
//        String type = ingredientInformationUpdateDTO.getType();
//        if (type != null) {
//            ingredientInformation.setType(type);
//        }
//
//        Double calorie = ingredientInformationUpdateDTO.getCalorie();
//        if (calorie != null) {
//            ingredientInformation.setCalorie(calorie);
//        }
//
//        Double calorie = ingredientInformationUpdateDTO.getCalorie();
//        if (calorie != null) {
//            ingredientInformation.setCalorie(calorie);
//        }
    }


}
