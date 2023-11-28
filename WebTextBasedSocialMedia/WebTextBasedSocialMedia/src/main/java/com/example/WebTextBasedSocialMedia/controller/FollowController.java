package com.example.WebTextBasedSocialMedia.controller;

import com.example.WebTextBasedSocialMedia.FollowerDTO;
import com.example.WebTextBasedSocialMedia.entity.Follow;
import com.example.WebTextBasedSocialMedia.entity.Post;
import com.example.WebTextBasedSocialMedia.services.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/follow")
public class FollowController {


    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/follow")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> followUser(@RequestBody Follow follow) {
        try {
            Follow followRel = followService.followUser(follow);
            return ResponseEntity.ok(followRel);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/find_followers")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<FollowerDTO>> getFollowers(@RequestParam String userEmail) {
        List<FollowerDTO> followers = followService.getFollowers(userEmail);
        return ResponseEntity.ok(followers);
    }
    @PostMapping("/unfollow")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> unfollowUser(@RequestBody Follow follow) {
        try {
            followService.unfollowUser(follow);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}