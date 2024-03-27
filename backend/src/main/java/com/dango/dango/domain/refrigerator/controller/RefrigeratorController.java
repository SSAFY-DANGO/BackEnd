package com.dango.dango.domain.refrigerator.controller;

import com.dango.dango.domain.log.entity.Log;
import com.dango.dango.domain.refrigerator.dto.RefrigeratorInfoResponse;
import com.dango.dango.domain.refrigerator.entity.Refrigerator;
import com.dango.dango.domain.refrigerator.service.RefrigeratorService;
import com.dango.dango.domain.user.entity.User;
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
        // 내 냉장고 정보를 조회한다.
        // 토큰으로 유저를 찾고, 그 유저에게 속한 냉장고 정보 조회
        User user = userService.findUserByToken();
        Long refrigeratorId = user.getRefrigeratorId();
        Refrigerator res = refrigeratorService.findRefrigeratorById(refrigeratorId);
        RefrigeratorInfoResponse response =
                RefrigeratorInfoResponse.builder()
                        .id(res.getId())
                        .nickname(res.getNickname())
                        .build();

        return new CustomResponse<>(HttpStatus.OK.value(), response, "내 냉장고 조회 완료");
    }

    @GetMapping("/{refrigeratorId}")
    @Operation(summary = "냉장고 안에 들어있는 품목을 조회한다", description = "식재료 로그 중 냉장고(id)에 속한 모든 물품을 조회합니다.")
    public CustomResponse<List<Log>> getRefrigeratorItems(@PathVariable Long refrigeratorId) {
        log.info("냉장고 품목 조회 들어옴");
        User user = userService.findUserByToken();
        Long userRefrigeratorId = user.getRefrigeratorId();
        List<Log> logList = refrigeratorService.getItems(refrigeratorId, userRefrigeratorId);
        return new CustomResponse<>(HttpStatus.OK.value(), logList, "냉장고 품목 리스트 조회 완료");
    }


    @PostMapping
    @Operation(summary = "내 냉장고를 등록한다.", description = "토큰으로 유저를 조회하고, 그 유저에게 냉장고를 등록합니다.")
    public CustomResponse<RefrigeratorInfoResponse> registerRefrigerator(@RequestBody String nickname) {
        log.info("냉장고 등록 요청 들어옴");
        // 냉장고를 등록한다.
        // 토큰으로 유저를 찾는다. -> 유저에게 속한 냉장고가 없으면 새로 등록한다.
        //성공 200 토큰x 400 냉장고 이미 있음 409
        User user = userService.findUserByToken();
        Long refrigeratorId = user.getRefrigeratorId();

        refrigeratorService.throwIfRefrigeratorDuplicated(refrigeratorId);
        Refrigerator res = refrigeratorService.registerRefrigerator(nickname, user);

        RefrigeratorInfoResponse response = RefrigeratorInfoResponse.builder()
                .id(res.getId()).nickname(res.getNickname()).build();

        return new CustomResponse<>(HttpStatus.OK.value(), response, "내 냉장고 등록 완료");
    }

    @PutMapping
    @Operation(summary = "냉장고 수정 요청", description = "토큰으로 유저를 찾고, 유저에게 속한 냉장고가 있으면 수정한다.")
    public CustomResponse<RefrigeratorInfoResponse> editRefrigerator(@RequestBody String nickname) {
        log.info("냉장고 수정 요청 들어옴");
        // 냉장고를 수정한다.
        // 토큰으로 유저를 찾는다 -> 유저에게 속한 냉장고가 있으면 수정한다.
        User user = userService.findUserByToken();
        Refrigerator res = refrigeratorService.editRefrigerator(user, nickname);

        RefrigeratorInfoResponse response = RefrigeratorInfoResponse.builder()
                .id(res.getId()).nickname(res.getNickname()).build();


        return new CustomResponse<>(HttpStatus.OK.value(), response, "내 냉장고 수정 완료");
    }

    @DeleteMapping
    @Operation(summary = "냉장고 삭제", description = "토큰으로 유저를 찾고, 등록된 냉장고를 삭제한다")
    public CustomResponse<Long> deleteRefrigerator() {
        log.info("냉장고 삭제 요청 들어옴");
        // 냉장고를 삭제한다.
        // 토큰으로 유저 찾기 -> 등록된 냉장고 삭제
        User user = userService.findUserByToken();
        Long refrigeratorId = user.getRefrigeratorId();
        refrigeratorService.throwIfRefrigeratorNotExist(refrigeratorId);
        refrigeratorService.deleteRefrigerator(refrigeratorId);
        return new CustomResponse<>(HttpStatus.OK.value(), refrigeratorId, refrigeratorId + "번 냉장고 삭제 완료");
    }
}
