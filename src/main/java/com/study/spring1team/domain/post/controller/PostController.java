package com.study.spring1team.domain.post.controller;

import com.study.spring1team.domain.post.dto.PostResponseDTO;
import com.study.spring1team.domain.post.entity.Post;
import com.study.spring1team.domain.post.service.PostService;
import com.study.spring1team.global.apiPayload.ApiResponse;
import com.study.spring1team.domain.post.dto.PostRequestDTO;
import com.study.spring1team.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ApiResponse<PostResponseDTO> createPost(
            @AuthenticationPrincipal Long userId,
            @Valid @RequestBody PostRequestDTO request
    ) {
        PostResponseDTO response = postService.createPost(userId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    @GetMapping
    public ApiResponse<List<Post>> getPosts() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, postService.getPostList());
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostResponseDTO> getPost(@PathVariable Long postId) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, postService.getPost(postId));
    }

    @PutMapping("/{postId}")
    public ApiResponse<String> updatePost(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long postId,
            @Valid @RequestBody PostRequestDTO request
    ) {
        postService.updatePost(userId, postId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "게시글이 성공적으로 수정되었습니다.");
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<String> deletePost(
            @AuthenticationPrincipal Long userId, @PathVariable Long postId) {
        postService.deletePost(userId,postId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "게시글이 성공적으로 삭제되었습니다.");
    }
}