package com.study.spring1team.domain.user.controller;

import com.study.spring1team.domain.user.dto.UserMeResponseDTO;
import com.study.spring1team.domain.user.service.UserService;
import com.study.spring1team.global.apiPayload.ApiResponse;
import com.study.spring1team.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ApiResponse<UserMeResponseDTO> getMe(@AuthenticationPrincipal Long userId) {
        UserMeResponseDTO response = userService.getMe(userId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}