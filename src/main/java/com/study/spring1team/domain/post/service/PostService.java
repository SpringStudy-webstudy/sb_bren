package com.study.spring1team.domain.post.service;

import com.study.spring1team.domain.post.dto.PostRequestDTO;
import com.study.spring1team.domain.post.dto.PostResponseDTO;
import com.study.spring1team.domain.post.entity.Post;
import com.study.spring1team.domain.post.repository.PostRepository;
import com.study.spring1team.domain.user.entity.User;
import com.study.spring1team.domain.user.repository.UserRepository;
import com.study.spring1team.global.apiPayload.code.GeneralErrorCode;
import com.study.spring1team.global.apiPayload.exception.GeneralException;
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

    public PostResponseDTO createPost(Long userId, PostRequestDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(user)
                .build();

        Post savedPost = postRepository.save(post);

        return new PostResponseDTO(savedPost);
    }

    @Transactional(readOnly = true)
    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PostResponseDTO getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));
        return new PostResponseDTO(post);
    }

    public void updatePost(Long userId, Long postId, PostRequestDTO request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        if (!post.getAuthor().getId().equals(userId)) {
            throw new GeneralException(GeneralErrorCode.FORBIDDEN);
        }

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
    }

    public void deletePost(Long userId, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        if (!post.getAuthor().getId().equals(userId)) {
            throw new GeneralException(GeneralErrorCode.FORBIDDEN);
        }

        postRepository.delete(post);
    }
}