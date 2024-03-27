package com.dango.dango.domain.log.controller;

import com.dango.dango.domain.log.dto.LogEditRequest;
import com.dango.dango.domain.log.dto.LogRegisterRequest;
import com.dango.dango.domain.log.entity.Log;
import com.dango.dango.domain.log.service.LogService;
import com.dango.dango.global.common.response.CustomResponse;
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
    public CustomResponse<Log> getLogDetail(@PathVariable Long id) {

        Log log = logService.findById(id);

        return new CustomResponse<>(HttpStatus.OK.value(), log, "식재료 로그 상세 정보 조회 성공");
    }

    @PostMapping
    public CustomResponse<Log> registerLog(@RequestBody @Valid LogRegisterRequest logRegisterRequest) {
        Log res = logService.registerLog(logRegisterRequest);
        return new CustomResponse<>(HttpStatus.OK.value(), res, "식재료 로그 추가 성공");
    }

    @PutMapping
    public CustomResponse<Log> editLog(@RequestBody @Valid LogEditRequest logEditRequest) {
        Log res = logService.editLog(logEditRequest);
        return new CustomResponse<>(HttpStatus.OK.value(), res, "식재료 로그 수정 성공");
    }

    @DeleteMapping
    public CustomResponse<String> deleteLog(@RequestBody Long id) {
        logService.deleteLog(id);
        return new CustomResponse<>(HttpStatus.OK.value(), "삭제 성공", "");
    }

}
