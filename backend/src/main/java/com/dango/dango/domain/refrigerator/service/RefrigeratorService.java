package com.dango.dango.domain.refrigerator.service;

import com.dango.dango.domain.log.entity.Log;
import com.dango.dango.domain.refrigerator.entity.Refrigerator;
import com.dango.dango.domain.user.entity.User;

import java.util.List;

public interface RefrigeratorService {
    Refrigerator findRefrigeratorById(Long id);

    boolean isRefrigeratorExist(Long id);

    void throwIfRefrigeratorDuplicated(Long id);

    void throwIfRefrigeratorNotExist(Long id);

    Refrigerator registerRefrigerator(String nickname);

    Refrigerator editRefrigerator(User user, String nickname);

    void deleteRefrigerator(Long id);

    List<Log> getItems(Long refrigeratorId, Long userRefrigeratorId);
}
