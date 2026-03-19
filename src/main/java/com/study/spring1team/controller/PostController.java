package com.study.spring1team.controller;

import com.study.spring1team.global.apiPayload.ApiResponse;
import com.study.spring1team.dto.PostRequestDTO;
import com.study.spring1team.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @PostMapping()
    public ApiResponse<String> createPost(@Valid @RequestBody PostRequestDTO request) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "게시글이 성공적으로 생성되었습니다.");
    }
}