package com.example.WebTextBasedSocialMedia.repository;

import com.example.WebTextBasedSocialMedia.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.postId IN :postIds ORDER BY c.created DESC")
    List<Comment> findLatestCommentsByPostIds(List<Long> postIds);
    List<Comment> findByPostIdAndUserEmail(Long postId, String user_email);
    // Additional query methods as needed

    @Query("SELECT c FROM Comment c WHERE c.postId = :postId ORDER BY c.created DESC")
    List<Comment> findLatestCommentsByPostId(Long postId, Pageable pageable);
}