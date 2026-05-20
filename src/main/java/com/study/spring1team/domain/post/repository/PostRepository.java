package com.study.spring1team.domain.post.repository;

import com.study.spring1team.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(
            value = """
            select p
            from Post p
            join fetch p.author
            left join fetch p.category
            where (:keyword is null
                   or p.title like concat('%', :keyword, '%')
                   or p.content like concat('%', :keyword, '%'))
              and (:startDateTime is null or p.createdAt >= :startDateTime)
              and (:endDateTime is null or p.createdAt < :endDateTime)
        """,
            countQuery = """
            select count(p)
            from Post p
            where (:keyword is null
                   or p.title like concat('%', :keyword, '%')
                   or p.content like concat('%', :keyword, '%'))
              and (:startDateTime is null or p.createdAt >= :startDateTime)
              and (:endDateTime is null or p.createdAt < :endDateTime)
        """
    )
    Page<Post> searchPosts(
            @Param("keyword") String keyword,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            Pageable pageable
    );
}