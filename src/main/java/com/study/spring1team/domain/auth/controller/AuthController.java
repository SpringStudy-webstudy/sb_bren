package com.study.spring1team.domain.auth.controller;

import com.study.spring1team.domain.auth.dto.LoginRequestDTO;
import com.study.spring1team.domain.auth.dto.LoginResponseDTO;
import com.study.spring1team.domain.auth.dto.SignupRequestDTO;
import com.study.spring1team.domain.auth.service.AuthService;
import com.study.spring1team.global.apiPayload.ApiResponse;
import com.study.spring1team.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> signup(@Valid @RequestBody SignupRequestDTO request) {
        authService.signup(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authService.login(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}