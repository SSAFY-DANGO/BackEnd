package com.dango.dango.domain.log.controller;

import com.dango.dango.domain.log.dto.LogEditRequest;
import com.dango.dango.domain.log.dto.LogRegisterRequest;
import com.dango.dango.domain.log.entity.Log;
import com.dango.dango.domain.log.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/log")
@Slf4j
public class LogController {
    private final LogService logService;

    @GetMapping("/{id}")
    public ResponseEntity<Log> getLogDetail(@PathVariable Long id) {

        Log log = logService.findById(id);

        return new ResponseEntity<>(log, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Log> registerLog(@RequestBody LogRegisterRequest logRegisterRequest) {
        Log res = logService.registerLog(logRegisterRequest);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Log> editLog(@RequestBody LogEditRequest logEditRequest) {
        Log res = logService.editLog(logEditRequest);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLog(@RequestBody Long id) {
        logService.deleteLog(id);
        return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
    }

}
