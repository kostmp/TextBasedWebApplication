package com.example.WebTextBasedSocialMedia.services;

import com.example.WebTextBasedSocialMedia.FollowerDTO;
import com.example.WebTextBasedSocialMedia.entity.Follow;
import com.example.WebTextBasedSocialMedia.repository.FollowRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowService {


    private final FollowRepository followRepository;

    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public Follow followUser(Follow follow) {
        if (followRepository.existsByFollowerEmailAndFollowingEmail(follow.getFollowerEmail(), follow.getFollowingEmail())) {
            throw new IllegalStateException("Already following this user.");
        }

       // Follow follow = new Follow();
        //follow.setFollowerEmail(followerEmail);
        //follow.setFollowingEmail(followingEmail);
        return followRepository.save(follow);
    }
    public List<FollowerDTO> getFollowers(String userEmail) {
        return followRepository.findFollowersByUserEmail(userEmail);
    }
    @Transactional
    public void unfollowUser(Follow follow1) {
        Optional<Follow> follow = followRepository.findByFollowerEmailAndFollowingEmail(follow1.getFollowerEmail(), follow1.getFollowingEmail());
        if (follow.isPresent()) {
            followRepository.delete(follow.get());
        } else {
            throw new IllegalStateException("Follow relationship not found.");
        }
    }

}