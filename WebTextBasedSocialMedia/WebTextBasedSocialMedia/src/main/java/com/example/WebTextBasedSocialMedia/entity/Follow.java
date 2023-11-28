package com.example.WebTextBasedSocialMedia.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "follows")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String followerEmail; // User ID of the follower

    @Column(nullable = false)
    private String followingEmail; // User ID of the following

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFollowerEmail() {
        return followerEmail;
    }

    public void setFollowerEmail(String followerEmail) {
        this.followerEmail = followerEmail;
    }

    public String getFollowingEmail() {
        return followingEmail;
    }

    public void setFollowingEmail(String followingEmail) {
        this.followingEmail = followingEmail;
    }
}