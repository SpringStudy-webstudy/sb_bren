package com.study.spring1team.domain.comment.controller;

import com.study.spring1team.domain.comment.dto.CommentRequestDTO;
import com.study.spring1team.domain.comment.service.CommentService;
import com.study.spring1team.global.apiPayload.ApiResponse;
import com.study.spring1team.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ApiResponse<String> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequestDTO request
    ) {
        commentService.createComment(postId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "댓글이 성공적으로 작성되었습니다.");
    }

    @DeleteMapping("/comments/{commentId}")
    public ApiResponse<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "댓글이 성공적으로 삭제되었습니다.");
    }
}