package com.dango.dango.domain.refrigerator.controller;

import com.dango.dango.domain.log.entity.Log;
import com.dango.dango.domain.refrigerator.dto.RefrigeratorInfoResponse;
import com.dango.dango.domain.refrigerator.service.RefrigeratorService;
import com.dango.dango.domain.user.service.UserService;
import com.dango.dango.global.common.response.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/refrigerator")
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;
    private final UserService userService;

    @GetMapping
    @Operation(summary = "내 냉장고를 조회한다", description = "토큰으로 유저를 조회하고 유저에게 속한 '냉장고'를 리턴합니다.")
    public CustomResponse<RefrigeratorInfoResponse> getRefrigeratorInfo() {
        log.info("냉장고 조회 요청 들어옴");
        RefrigeratorInfoResponse res = refrigeratorService.getRefrigerator();

        return new CustomResponse<>(HttpStatus.OK.value(), res, "내 냉장고 조회 완료");
    }

    @GetMapping("/{refrigeratorNickname}")
    @Operation(summary = "냉장고 안에 들어있는 품목을 조회한다", description = "식재료 로그 중 냉장고(닉네임)에 속한 모든 물품을 조회합니다.")
    public CustomResponse<List<Log>> getRefrigeratorItems(@PathVariable String refrigeratorNickname) {
        log.info("냉장고 품목 조회 들어옴");
        List<Log> logList = refrigeratorService.getItems(refrigeratorNickname);
        return new CustomResponse<>(HttpStatus.OK.value(), logList, "냉장고 품목 리스트 조회 완료");
    }

    @GetMapping("/old/{refrigeratorNickname}")
    @Operation(summary = "냉장고 안에 들어있는 품목 중 오래된 물건을 조회한다.", description= "{refrigeratorNickname} 냉장고에서 들어온 날짜가 ?t={t} 이상 경과한 물품을 모두 조회합니다. t가 전달되지 않으면 기본값(10일)이 적용됩니다.")
    public CustomResponse<List<Log>> getOldItems(@PathVariable String refrigeratorNickname, @RequestParam(defaultValue = "10",name ="t") Long day) {
        log.info("오래된 물품 조회 들어옴");
        List<Log> oldLogList = refrigeratorService.getOldItems(refrigeratorNickname, day);

        return new CustomResponse<>(HttpStatus.OK.value(), oldLogList, "오래된 품목 리스트 조회 완료");
    }



    @PostMapping
    @Operation(summary = "내 냉장고를 등록한다.", description = "토큰으로 유저를 조회하고, 그 유저에게 냉장고를 등록합니다.")
    public CustomResponse<RefrigeratorInfoResponse> registerRefrigerator(@RequestBody String nickname) {
        log.info("냉장고 등록 요청 들어옴");

        RefrigeratorInfoResponse res = refrigeratorService.registerRefrigerator(nickname);
        return new CustomResponse<>(HttpStatus.OK.value(), res, "내 냉장고 등록 완료");
    }

    @PutMapping
    @Operation(summary = "냉장고 수정 요청", description = "토큰으로 유저를 찾고, 유저에게 속한 냉장고가 있으면 수정한다.")
    public CustomResponse<RefrigeratorInfoResponse> editRefrigerator(@RequestBody String nickname) {
        log.info("냉장고 수정 요청 들어옴");
        // 냉장고를 수정한다.
        // 토큰으로 유저를 찾는다 -> 유저에게 속한 냉장고가 있으면 수정한다.
        RefrigeratorInfoResponse res = refrigeratorService.editRefrigerator(nickname);
        return new CustomResponse<>(HttpStatus.OK.value(), res, "내 냉장고 수정 완료");
    }

    @DeleteMapping
    @Operation(summary = "냉장고 삭제", description = "토큰으로 유저를 찾고, 등록된 냉장고를 삭제한다")
    public CustomResponse<Long> deleteRefrigerator() {
        log.info("냉장고 삭제 요청 들어옴");
        // 냉장고를 삭제한다.
        // 토큰으로 유저 찾기 -> 등록된 냉장고 삭제
        Long deleted = refrigeratorService.deleteRefrigerator();
        return new CustomResponse<>(HttpStatus.OK.value(), deleted, deleted + "번 냉장고 삭제 완료");
    }
}
