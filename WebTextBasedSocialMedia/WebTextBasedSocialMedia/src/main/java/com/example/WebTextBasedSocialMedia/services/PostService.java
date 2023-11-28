package com.example.WebTextBasedSocialMedia.services;

import com.example.WebTextBasedSocialMedia.PostWithComments;
import com.example.WebTextBasedSocialMedia.entity.Comment;
import com.example.WebTextBasedSocialMedia.entity.Post;
import com.example.WebTextBasedSocialMedia.repository.CommentRepository;
import com.example.WebTextBasedSocialMedia.repository.PostRepository;
import com.example.WebTextBasedSocialMedia.repository.ViewFollowingPostsRepository;
import com.example.WebTextBasedSocialMedia.repository.ViewOwnPostsWithComments;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final ViewFollowingPostsRepository viewFollowingPostsRepository;
    private final CommentRepository commentRepository;
   // private final ViewOwnPostsWithComments viewOwnPostsWithComments;
    public PostService(PostRepository postRepository, ViewFollowingPostsRepository viewFollowingPostsRepository, CommentRepository commentRepository, ViewOwnPostsWithComments viewOwnPostsWithComments) {
        this.postRepository = postRepository;
        this.viewFollowingPostsRepository = viewFollowingPostsRepository;
        this.commentRepository = commentRepository;
        //this.viewOwnPostsWithComments = viewOwnPostsWithComments;
    }

    public Optional<Post> getById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        if (post.getId() == null) {
            post.setCreated(LocalDateTime.now());
        }
        return postRepository.save(post);
    }

    public List<Post> getPostsByFollowedUsers(String userEmail) {
        return viewFollowingPostsRepository.findPostsByFollowed(userEmail);
    }
    public void delete(Post post) {
        postRepository.delete(post);
    }

    public List<PostWithComments> getOwnPostsAndComments(String userEmail) {
        List<Post> posts = postRepository.findAllByUserEmailOrderByCreatedDesc(userEmail);
        return posts.stream().map(post -> {
            List<Comment> comments = commentRepository.findLatestCommentsByPostId(post.getId(), PageRequest.of(0, 100));
            return new PostWithComments(post, comments);
        }).collect(Collectors.toList());
    }
    public List<PostWithComments> getRelevantPostsWithComments(String userEmail) {
        // Get user's own posts
        List<Post> ownPosts = postRepository.findAllByUserEmail(userEmail);

        // Get posts from followed users
        List<Post> followedUserPosts = postRepository.findAllByFollowedUsers(userEmail);

        // Combine and get unique posts
        List<Post> combinedPosts = Stream.concat(ownPosts.stream(), followedUserPosts.stream())
                .distinct()
                .collect(Collectors.toList());

        // Create a Pageable object to fetch a limited number of comments
        Pageable pageable = PageRequest.of(0, 1);

        // Map and return posts with their latest comments
        return combinedPosts.stream().map(post -> {
            List<Comment> postComments = commentRepository.findLatestCommentsByPostId(post.getId(), pageable);
            return new PostWithComments(post, new ArrayList<>(postComments));
        }).collect(Collectors.toList());
    }
}
