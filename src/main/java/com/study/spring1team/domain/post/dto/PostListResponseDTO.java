package com.study.spring1team.domain.post.dto;

import com.study.spring1team.domain.post.entity.Post;
import java.time.LocalDateTime;

public record PostListResponseDTO(
        Long postId,
        String title,
        String writerName,
        String categoryName,
        LocalDateTime createdAt
) {
    public static PostListResponseDTO from(Post post) {
        return new PostListResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getAuthor().getName(),
                post.getCategory() != null ? post.getCategory().getName() : null,
                post.getCreatedAt()
        );
    }
}