package com.study.spring1team.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDTO {

    @NotBlank(message = "댓글을 입력해주세요.")
    private String content;
}