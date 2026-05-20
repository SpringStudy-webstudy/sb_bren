package com.study.spring1team.domain.post.repository;

import com.study.spring1team.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}