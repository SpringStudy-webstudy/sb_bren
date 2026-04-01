package com.study.spring1team.domain.comment.repository;

import com.study.spring1team.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}