package com.example.WebTextBasedSocialMedia.repository;

import com.example.WebTextBasedSocialMedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserEmail(String userEmail);

    @Query("SELECT p FROM Post p WHERE p.userEmail IN " +
            "(SELECT f.followingEmail FROM Follow f WHERE f.followerEmail = :userEmail) " +
            "ORDER BY p.created DESC")
    List<Post> findAllByFollowedUsers(String userEmail);
    List<Post> findAllByUserEmailOrderByCreatedDesc(String userEmail);
}
