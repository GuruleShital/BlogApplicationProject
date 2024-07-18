package com.BlogApi.Blog_app_apis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApi.Blog_app_apis.payloads.PostDto;
import com.BlogApi.Blog_app_apis.services.PostService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/apis/")
public class PostController {
    
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    
    @Autowired
    private PostService postService;
    
    // Creating a new post
    @PostMapping("user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        logger.info("Creating post for user ID: {} and category ID: {}", userId, categoryId);
        PostDto post = this.postService.createPost(postDto, userId, categoryId);
        logger.info("Post created: {}", post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    // Get posts by user ID
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        logger.info("Fetching posts for user ID: {}", userId);
        List<PostDto> posts = this.postService.getAllPostByUserId(userId);
        logger.info("Fetched {} posts for user ID: {}", posts.size(), userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Get posts by category ID
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        logger.info("Fetching posts for category ID: {}", categoryId);
        List<PostDto> posts = this.postService.getAllPostByCategoryId(categoryId);
        logger.info("Fetched {} posts for category ID: {}", posts.size(), categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Get all posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPost() {
        logger.info("Fetching all posts");
        List<PostDto> allPosts = this.postService.getAllpost();
        logger.info("Fetched {} posts", allPosts.size());
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    // Get post details by post ID
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        logger.info("Fetching post with ID: {}", postId);
        PostDto post = this.postService.getPostById(postId);
        logger.info("Fetched post: {}", post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
