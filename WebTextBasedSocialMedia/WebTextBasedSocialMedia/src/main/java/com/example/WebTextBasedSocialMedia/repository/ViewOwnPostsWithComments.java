package com.example.WebTextBasedSocialMedia.repository;

import com.example.WebTextBasedSocialMedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewOwnPostsWithComments extends JpaRepository<Post, Long> {

   // @Query("SELECT p FROM Post p LEFT JOIN FETCH p.comments WHERE p.user_email = :userEmail ORDER BY p.created DESC")
    //List<Post> findPostsByUserIdWithComments(String userEmail);
   // List<Post> findAllByUserEmailOrderByCreatedAtDesc(String userEmail);
}