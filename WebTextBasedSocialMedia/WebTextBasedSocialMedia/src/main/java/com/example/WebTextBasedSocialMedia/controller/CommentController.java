package com.example.WebTextBasedSocialMedia.controller;

import com.example.WebTextBasedSocialMedia.entity.Comment;
import com.example.WebTextBasedSocialMedia.entity.Post;
import com.example.WebTextBasedSocialMedia.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@PreAuthorize("hasRole('ROLE_USER')")
public class CommentController {


    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String createNewComment(Model model) {

        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        return "comment_new";
    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        try {
            Comment createdComment = commentService.createComment(comment);
            return ResponseEntity.ok(createdComment);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}