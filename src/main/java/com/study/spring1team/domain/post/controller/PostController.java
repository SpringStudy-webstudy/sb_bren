package com.study.spring1team.domain.post.controller;

import com.study.spring1team.domain.post.entity.Post;
import com.study.spring1team.domain.post.service.PostService;
import com.study.spring1team.global.apiPayload.ApiResponse;
import com.study.spring1team.domain.post.dto.PostRequestDTO;
import com.study.spring1team.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping()
    public ApiResponse<String> createPost(@Valid @RequestBody PostRequestDTO request) {
        postService.createPost(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "게시글이 성공적으로 생성되었습니다.");
    }

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPostList();
    }
}