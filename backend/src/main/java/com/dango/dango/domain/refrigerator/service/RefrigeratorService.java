package com.dango.dango.domain.refrigerator.service;

import com.dango.dango.domain.log.entity.Log;
import com.dango.dango.domain.refrigerator.dto.RefrigeratorInfoResponse;
import com.dango.dango.domain.refrigerator.entity.Refrigerator;

import java.util.List;

public interface RefrigeratorService {
    Refrigerator findRefrigeratorById(Long id);

    boolean isRefrigeratorExist(Long id);

    void throwIfRefrigeratorDuplicated(Long id);

    void throwIfRefrigeratorNotExist(Long id);

    RefrigeratorInfoResponse registerRefrigerator(String nickname);

    RefrigeratorInfoResponse editRefrigerator(String nickname);

    Long deleteRefrigerator();

    List<Log> getItems(String refrigeratorNickname);

    RefrigeratorInfoResponse getRefrigerator();

    List<Log> getOldItems(String refrigeratorNickname, Long day);
}
