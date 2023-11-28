package com.example.WebTextBasedSocialMedia.controller;

import com.example.WebTextBasedSocialMedia.PostWithComments;
import com.example.WebTextBasedSocialMedia.entity.Post;
import com.example.WebTextBasedSocialMedia.services.AccountService;
import com.example.WebTextBasedSocialMedia.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    private final PostService postService;
    private final AccountService accountService;

    public PostController(PostService postService, AccountService accountService) {
        this.postService = postService;
        this.accountService = accountService;
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {

        // find post by id
        Optional<Post> optionalPost = this.postService.getById(id);

        // if post exists put it in model
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post";
        } else {
            return "404";
        }
    }
    @GetMapping("/posts/new")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String createNewPost(Model model) {

        Post post = new Post();
        model.addAttribute("post", post);
        return "post_new";
    }
    @PostMapping("/posts/new")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }
    @GetMapping("/feed")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Post>> getFeedForCurrentUser(@RequestParam String userEmail) {
        List<Post> feed = postService.getPostsByFollowedUsers(userEmail);
        return ResponseEntity.ok(feed);
    }
    @GetMapping("/own")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<PostWithComments>> getOwnPostsAndComments(@RequestParam String userEmail) {
        List<PostWithComments> postsWithComments = postService.getOwnPostsAndComments(userEmail);
        return ResponseEntity.ok(postsWithComments);
    }
    @GetMapping("/relevant-comments")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<PostWithComments>> getRelevantPostsWithComments(@RequestParam String userEmail) {
        List<PostWithComments> postsWithComments = postService.getRelevantPostsWithComments(userEmail);
        return ResponseEntity.ok(postsWithComments);
    }
}
