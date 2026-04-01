package com.study.spring1team.domain.post.dto;

import com.study.spring1team.domain.post.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor() != null ? post.getAuthor().getName() : null;
    }
}