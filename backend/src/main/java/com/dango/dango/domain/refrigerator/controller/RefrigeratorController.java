package com.dango.dango.domain.refrigerator.controller;

import com.dango.dango.domain.refrigerator.dto.RefrigeratorInfoResponse;
import com.dango.dango.domain.refrigerator.entity.Refrigerator;
import com.dango.dango.domain.refrigerator.service.RefrigeratorService;
import com.dango.dango.domain.user.entity.User;
import com.dango.dango.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/refrigerator")
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<RefrigeratorInfoResponse> getRefrigeratorInfo() {
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

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RefrigeratorInfoResponse> registerRefrigerator(@RequestBody String nickname) {
        log.info("냉장고 등록 요청 들어옴");
        // 냉장고를 등록한다.
        // 토큰으로 유저를 찾는다. -> 유저에게 속한 냉장고가 없으면 새로 등록한다.
        //성공 200 토큰x 400 냉장고 이미 있음 409
        User user = userService.findUserByToken();
        Long refrigeratorId = user.getRefrigeratorId();

        refrigeratorService.throwIfRefrigeratorDuplicated(refrigeratorId);
        Refrigerator res = refrigeratorService.registerRefrigerator(nickname);

        // 등록 완료한 냉장고를 유저에게 속하도록
        user.setRefrigeratorId(res.getId());
        RefrigeratorInfoResponse response = RefrigeratorInfoResponse.builder()
                .id(res.getId()).nickname(res.getNickname()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RefrigeratorInfoResponse> editRefrigerator(@RequestBody String nickname) {
        log.info("냉장고 수정 요청 들어옴");
        // 냉장고를 수정한다.
        // 토큰으로 유저를 찾는다 -> 유저에게 속한 냉장고가 있으면 수정한다.
        User user = userService.findUserByToken();
        Refrigerator res = refrigeratorService.editRefrigerator(user, nickname);

        RefrigeratorInfoResponse response = RefrigeratorInfoResponse.builder()
                .id(res.getId()).nickname(res.getNickname()).build();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRefrigerator() {
        log.info("냉장고 삭제 요청 들어옴");
        // 냉장고를 삭제한다.
        // 토큰으로 유저 찾기 -> 등록된 냉장고 삭제
        User user = userService.findUserByToken();
        Long refrigeratorId = user.getRefrigeratorId();
        refrigeratorService.throwIfRefrigeratorNotExist(refrigeratorId);
        refrigeratorService.deleteRefrigerator(user);
        return new ResponseEntity<>("냉장고 삭제 완료", HttpStatus.OK);
    }


}
