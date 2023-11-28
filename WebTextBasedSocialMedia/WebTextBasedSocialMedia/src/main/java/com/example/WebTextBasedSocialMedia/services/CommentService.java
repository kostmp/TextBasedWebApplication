package com.example.WebTextBasedSocialMedia.services;

import com.example.WebTextBasedSocialMedia.entity.Comment;
import com.example.WebTextBasedSocialMedia.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private static final int MAX_COMMENTS_PER_POST = 10;


    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        List<Comment> existingComments = commentRepository.findByPostIdAndUserEmail(comment.getPostId(), comment.getUserEmail());
        if (existingComments.size() >= MAX_COMMENTS_PER_POST) {
            throw new IllegalStateException("Maximum comment limit reached for this post.");
        }

        return commentRepository.save(comment);
    }
}