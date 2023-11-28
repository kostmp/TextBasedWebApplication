package com.example.WebTextBasedSocialMedia.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String userEmail;

        @Column(nullable = false)
        private Long postId;

        @Column(length = 1000, nullable = false)
        private String comment;

        @Column(nullable = false)
        @CreationTimestamp
        private LocalDateTime created;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUserEmail() {
                return userEmail;
        }

        public void setUserEmail(String user_email) {
                this.userEmail = user_email;
        }

        public Long getPostId() {
                return postId;
        }

        public void setPostId(Long postId) {
                this.postId = postId;
        }

        public String getComment() {
                return comment;
        }

        public void setComment(String comment) {
                this.comment = comment;
        }

        public LocalDateTime getCreated() {
                return created;
        }

        public void setCreated(LocalDateTime created) {
                this.created = created;
        }
}

