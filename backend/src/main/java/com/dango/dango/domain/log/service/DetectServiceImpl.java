package com.dango.dango.domain.log.service;

import com.dango.dango.domain.detectLog.service.DetectLogService;
import com.dango.dango.domain.ingredientinformation.service.IngredientInformationService;
import com.dango.dango.domain.log.dto.LogRegisterRequest;
import com.dango.dango.domain.log.entity.Log;
import com.dango.dango.domain.refrigerator.service.RefrigeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DetectServiceImpl implements DetectService {
    private final DetectLogService detectLogService;
    private final LogService logService;
    private final TrashService trashService;
    private final RefrigeratorService refrigeratorService;
    private final IngredientInformationService ingredientInformationService;

    @Override
    public Set<String> getLatestDetectLog(String device) {
        List<String> top = detectLogService.getAllIngredients(device, "Top");
        List<String> back = detectLogService.getAllIngredients(device, "Back");

        List<String> ingredients = new ArrayList<>(top);
        ingredients.addAll(back);

        return new HashSet<>(ingredients);
    }

    @Override
    public void manageIngredients(String device) {
        // Load Ingredients Info
        Set<String> capturedIngredients = getLatestDetectLog(device);
        List<Log> existingIngredients = logService.getItems(device, true);
        List<Log> deletedIngredients = logService.getItems(device, false);

        Map<String, Long> existingIngredientsMap = new HashMap<>();
        for (Log log : existingIngredients) {
            existingIngredientsMap.put(log.getName(), log.getId());
        }

        Map<String, Long> deletedIngredientsMap = new HashMap<>();
        for (Log log : deletedIngredients) {
            deletedIngredientsMap.put(log.getName(), log.getId());
        }

        for (String capturedIngredient : capturedIngredients) {
            if (!existingIngredientsMap.containsKey(capturedIngredient)) {
                if (deletedIngredientsMap.containsKey(capturedIngredient)) {
                    trashService.restoreLog(deletedIngredientsMap.get(capturedIngredient));
                } else {
                    LogRegisterRequest log = new LogRegisterRequest();
                    log.setRefrigeratorId(refrigeratorService.findRefrigeratorByNickname(device).getId());
                    log.setName(capturedIngredient);
                    log.setCategory(
                            ingredientInformationService.findIngredientInformationByName(capturedIngredient).getType());
                    log.setType(0);
                    logService.registerLog(log);
                }
            }
        }

        for (Log existingIngredient : existingIngredients) {
            if (!capturedIngredients.contains(existingIngredient.getName())) {
                logService.deleteLog(existingIngredient.getId());
            }
        }

    }
}
