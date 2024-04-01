package com.dango.dango.domain.log.controller;

import com.dango.dango.domain.log.entity.Log;
import com.dango.dango.domain.log.service.TrashService;
import com.dango.dango.global.common.response.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trash")
@Slf4j
public class TrashController {
    private final TrashService trashService;

    @GetMapping("/{refrigeratorNickname}")
    @Operation(summary = "휴지통 내부의 삭제된 식재료를 조회합니다.", description = "특정 냉장고(nickname 기준)의 삭제된 식재료 목록을 조회합니다.")
    public CustomResponse<List<Log>> getTrashItems(@PathVariable String refrigeratorNickname) {
        List<Log> logList = trashService.getItems(refrigeratorNickname);
        return new CustomResponse<>(HttpStatus.OK.value(), logList, "휴지통 내부의 식재료 목록이 조회되었습니다.");
    }

    @PutMapping("/{id}")
    @Operation(summary = "식재료 로그를 복원합니다.", description = "식재료가 잘못 삭제되어 있는 경우, 식재료 로그를 복원합니다.")
    public CustomResponse<Log> restoreLog(@PathVariable Long id) {
        Log res = trashService.restoreLog(id);
        return new CustomResponse<>(HttpStatus.OK.value(), res, "식재료가 복원되었습니다.");
    }
}
