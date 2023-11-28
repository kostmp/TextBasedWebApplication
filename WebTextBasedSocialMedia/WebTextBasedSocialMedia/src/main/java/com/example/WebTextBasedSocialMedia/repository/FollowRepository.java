package com.example.WebTextBasedSocialMedia.repository;

import com.example.WebTextBasedSocialMedia.FollowerDTO;
import com.example.WebTextBasedSocialMedia.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query("SELECT new com.example.WebTextBasedSocialMedia.FollowerDTO(u.email) FROM Account u WHERE u.email IN " +
            "(SELECT f.followerEmail FROM Follow f WHERE f.followingEmail = :userEmail)")
    List<FollowerDTO> findFollowersByUserEmail(String userEmail);
    List<Follow> findByFollowerEmail(String followerEmail);
    List<Follow> findByFollowingEmail(String followingEmail);
    boolean existsByFollowerEmailAndFollowingEmail(String followerEmail, String followingEmail);
    Optional<Follow> findByFollowerEmailAndFollowingEmail(String followerEmail, String followingEmail);
}