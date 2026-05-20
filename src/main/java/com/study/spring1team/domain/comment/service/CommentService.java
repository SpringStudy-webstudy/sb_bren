package com.study.spring1team.domain.comment.service;

import com.study.spring1team.domain.comment.dto.CommentRequestDTO;
import com.study.spring1team.domain.comment.entity.Comment;
import com.study.spring1team.domain.comment.repository.CommentRepository;
import com.study.spring1team.domain.post.entity.Post;
import com.study.spring1team.domain.post.repository.PostRepository;
import com.study.spring1team.domain.user.entity.User;
import com.study.spring1team.domain.user.repository.UserRepository;
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
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        User user = userRepository.findById(DEFAULT_USER_ID)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .author(user)
                .post(post)
                .build();

        return commentRepository.save(comment).getId();
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));

        commentRepository.delete(comment);
    }
}