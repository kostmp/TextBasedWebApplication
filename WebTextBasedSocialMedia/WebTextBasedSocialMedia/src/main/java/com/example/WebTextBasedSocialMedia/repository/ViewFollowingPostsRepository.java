package com.example.WebTextBasedSocialMedia.repository;

import com.example.WebTextBasedSocialMedia.entity.Comment;
import com.example.WebTextBasedSocialMedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViewFollowingPostsRepository extends JpaRepository<Comment, Long> {
      //  @Query("SELECT c FROM Comment c WHERE c.postId = :postId ORDER BY c.createdAt DESC")
        //List<Comment> findLatestCommentsByPostId(Long postId);

    @Query("SELECT p FROM Post p JOIN Follow f ON p.userEmail = f.followingEmail WHERE f.followerEmail = :currentUserEmail ORDER BY p.created DESC")
    List<Post> findPostsByFollowed(@Param("currentUserEmail") String currentUserEmail);
    }