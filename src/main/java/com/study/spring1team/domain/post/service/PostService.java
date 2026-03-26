package com.study.spring1team.domain.post.service;

import com.study.spring1team.domain.post.dto.PostRequestDTO;
import com.study.spring1team.domain.post.entity.Post;
import com.study.spring1team.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Long createPost(PostRequestDTO request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        return postRepository.save(post).getId();
    }

    public List<Post> getPostList() {
        return postRepository.findAll();
    }
}