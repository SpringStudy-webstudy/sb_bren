package com.study.spring1team.domain.post.service;

import com.study.spring1team.domain.post.dto.PostRequestDTO;
import com.study.spring1team.domain.post.dto.PostResponseDTO;
import com.study.spring1team.domain.post.entity.Post;
import com.study.spring1team.domain.post.repository.PostRepository;
import com.study.spring1team.domain.user.entity.User;
import com.study.spring1team.domain.user.repository.UserRepository;
import com.study.spring1team.domain.comment.entity.Comment;
import com.study.spring1team.domain.comment.repository.CommentRepository;
import com.study.spring1team.global.apiPayload.code.GeneralErrorCode;
import com.study.spring1team.global.apiPayload.exception.GeneralException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.study.spring1team.domain.post.dto.PostListResponseDTO;
import com.study.spring1team.domain.post.dto.PostSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public PostResponseDTO createPost(Long userId, PostRequestDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(user)
                .build();

        Post savedPost = postRepository.save(post);

        Comment comment = Comment.builder()
                .content(request.getCommentContent())
                .author(user)
                .post(savedPost)
                .build();

        commentRepository.save(comment);

//        System.out.println("createPost 진입");
//        System.out.println("댓글 저장 후 예외 발생 직전");
//        throw new RuntimeException("rollback test");
        return new PostResponseDTO(savedPost);
    }

    @Transactional(readOnly = true)
    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<PostListResponseDTO> getPostList(PostSearchCondition condition, int page, int size) {
        String keyword = condition.keyword();

        if (keyword != null && keyword.isBlank()) {
            keyword = null;
        }

        LocalDateTime startDateTime = condition.startDate() != null
                ? condition.startDate().atStartOfDay()
                : null;

        LocalDateTime endDateTime = condition.endDate() != null
                ? condition.endDate().plusDays(1).atStartOfDay()
                : null;

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        return postRepository.searchPosts(
                        keyword,
                        startDateTime,
                        endDateTime,
                        pageable
                )
                .map(PostListResponseDTO::from);
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