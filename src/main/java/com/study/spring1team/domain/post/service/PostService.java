package com.study.spring1team.domain.post.service;

import com.study.spring1team.domain.post.dto.PostRequestDTO;
import com.study.spring1team.domain.post.dto.PostResponseDTO;
import com.study.spring1team.domain.post.entity.Post;
import com.study.spring1team.domain.post.repository.PostRepository;
import com.study.spring1team.domain.user.entity.User;
import com.study.spring1team.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private static final Long DEFAULT_USER_ID = 1L;

    public Long createPost(PostRequestDTO request) {
        User user = userRepository.findById(DEFAULT_USER_ID)
                .orElseThrow(() -> new IllegalArgumentException("기본 사용자가 없습니다."));

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(user)
                .build();

        return postRepository.save(post).getId();
    }

    @Transactional(readOnly = true)
    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PostResponseDTO getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new PostResponseDTO(post);
    }

    public void updatePost(Long postId, PostRequestDTO request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        if (!post.getAuthor().getId().equals(DEFAULT_USER_ID)) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        if (!post.getAuthor().getId().equals(DEFAULT_USER_ID)) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        postRepository.delete(post);
    }
}