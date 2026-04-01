package com.study.spring1team.domain.comment.dto;

import com.study.spring1team.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDTO {
    private Long id;
    private String content;
    private String authorName;

    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.authorName = comment.getAuthor() != null ? comment.getAuthor().getName() : null;
    }
}