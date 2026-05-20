package com.study.spring1team.domain.ping.controller;

import com.study.spring1team.global.apiPayload.ApiResponse;
import com.study.spring1team.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PingController {

    @GetMapping("/ping")
    public ApiResponse ping() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,"pong");
    }
}