package com.dango.dango.domain.log.service;

import java.util.Set;

public interface DetectService {

	Set<String> getLatestDetectLog(String device);

	void manageIngredients(String device);
}
