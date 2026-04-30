package com.study.spring1team.domain.comment.service;

import com.study.spring1team.domain.comment.dto.CommentRequestDTO;
import com.study.spring1team.domain.comment.entity.Comment;
import com.study.spring1team.domain.comment.repository.CommentRepository;
import com.study.spring1team.domain.post.entity.Post;
import com.study.spring1team.domain.post.repository.PostRepository;
import com.study.spring1team.domain.user.entity.User;
import com.study.spring1team.domain.user.repository.UserRepository;
import com.study.spring1team.global.apiPayload.code.GeneralErrorCode;
import com.study.spring1team.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 유저 고정값 설정
    private static final Long DEFAULT_USER_ID = 1L;

    public Long createComment(Long postId, CommentRequestDTO request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() ->  new GeneralException(GeneralErrorCode.NOT_FOUND));

        User user = userRepository.findById(DEFAULT_USER_ID)
                .orElseThrow(() ->  new GeneralException(GeneralErrorCode.NOT_FOUND));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .author(user)
                .post(post)
                .build();

        return commentRepository.save(comment).getId();
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        if (!comment.getAuthor().getId().equals(DEFAULT_USER_ID)) {
            throw new GeneralException(GeneralErrorCode.FORBIDDEN);
        }

        commentRepository.delete(comment);
    }
}