package com.example.WebTextBasedSocialMedia;

import com.example.WebTextBasedSocialMedia.entity.Comment;
import com.example.WebTextBasedSocialMedia.entity.Post;

import java.util.List;

public class PostWithComments {
    private Post post;
    private List<Comment> comments;

    public PostWithComments(Post post, List<Comment> comments) {
        this.post = post;
        this.comments = comments;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}