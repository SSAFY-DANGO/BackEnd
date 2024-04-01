package com.dango.dango.domain.log.service;

import com.dango.dango.domain.log.entity.Log;

import java.util.List;

public interface TrashService {
    List<Log> getItems(String refrigeratorNickname);
    Log restoreLog(Long id);
}
