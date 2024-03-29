package com.dango.dango.domain.log.controller;

import com.dango.dango.domain.log.dto.LogDetailResponse;
import com.dango.dango.domain.log.dto.LogEditRequest;
import com.dango.dango.domain.log.dto.LogRegisterRequest;
import com.dango.dango.domain.log.entity.Log;
import com.dango.dango.domain.log.service.LogService;
import com.dango.dango.global.common.response.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/log")
@Slf4j
public class LogController {
    private final LogService logService;

    @GetMapping("/{id}")
    @Operation(summary = "개별 식재료 로그의 상세정보를 조회합니다.", description = "식재료 로그의 id를 기준으로 상세 사항(엔티티 모든 정보 + 속한 원재료가 있으면 원재료 정보까지)을 조회합니다.")
    public CustomResponse<LogDetailResponse> getLogDetail(@PathVariable Long id) {

        LogDetailResponse res = logService.getLogDetailById(id);

        return new CustomResponse<>(HttpStatus.OK.value(), res, "식재료 로그 상세 정보 조회 성공");
    }

    @PostMapping
    @Operation(summary = "식재료 로그를 추가합니다. (냉장고에 음식넣기)", description = "식재료 로그를 추가합니다. registerDTO에 맞게 요청이 도착해야 합니다.")
    public CustomResponse<Log> registerLog(@RequestBody @Valid LogRegisterRequest logRegisterRequest) {
        Log res = logService.registerLog(logRegisterRequest);
        return new CustomResponse<>(HttpStatus.OK.value(), res, "식재료 로그 추가 성공");
    }

    @PutMapping
    @Operation(summary = "식재료 로그를 수정합니다. (냉장고 안 정보가 잘못되어있을 때, 넣고 빼고 등)", description = "식재료 로그를 수정합니다. editDTO에 맞게 도착해야 합니다.")
    public CustomResponse<Log> editLog(@RequestBody @Valid LogEditRequest logEditRequest) {
        Log res = logService.editLog(logEditRequest);
        return new CustomResponse<>(HttpStatus.OK.value(), res, "식재료 로그 수정 성공");
    }

    @DeleteMapping
    @Operation(summary = "식재료 로그를 삭제합니다.", description = "식재료 로그의 id를 기준으로 로그를 삭제합니다.")
    public CustomResponse<String> deleteLog(@RequestBody Long id) {
        logService.deleteLog(id);
        return new CustomResponse<>(HttpStatus.OK.value(), "삭제 성공", "");
    }

}
