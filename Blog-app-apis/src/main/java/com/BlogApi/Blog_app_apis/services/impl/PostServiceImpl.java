package com.BlogApi.Blog_app_apis.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApi.Blog_app_apis.entity.Category;
import com.BlogApi.Blog_app_apis.entity.Post;
import com.BlogApi.Blog_app_apis.entity.User;
import com.BlogApi.Blog_app_apis.exceptions.ResourceNotFoundException;
import com.BlogApi.Blog_app_apis.payloads.PostDto;
import com.BlogApi.Blog_app_apis.repository.CategoryRepo;
import com.BlogApi.Blog_app_apis.repository.PostRepo;
import com.BlogApi.Blog_app_apis.repository.UserRepo;
import com.BlogApi.Blog_app_apis.services.PostService;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        logger.info("Creating post with data: {}, userId: {}, categoryId: {}", postDto, userId, categoryId);

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost = postRepo.save(post);
        logger.info("Post created: {}", savedPost);

        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        // Implementation of updatePost method
        return null;
    }

    @Override
    public void deletePost(Integer postId) {
        // Implementation of deletePost method
    }

    @Override
    public List<PostDto> getAllpost() {
        logger.info("Fetching all posts");
        List<Post> allPosts = postRepo.findAll();
        List<PostDto> postDtos = allPosts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        logger.info("Fetched {} posts", postDtos.size());
        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        logger.info("Fetching post with ID: {}", postId);
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        // Implementation of searchPosts method
        return null;
    }

    @Override
    public List<PostDto> getAllPostByCategoryId(Integer categoryId) {
        logger.info("Fetching posts by categoryId: {}", categoryId);
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Post> posts = postRepo.findByCategory(category);
        List<PostDto> postDtos = posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        logger.info("Fetched {} posts for categoryId: {}", postDtos.size(), categoryId);
        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostByUserId(Integer userId) {
        logger.info("Fetching posts by userId: {}", userId);
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        List<Post> posts = postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        logger.info("Fetched {} posts for userId: {}", postDtos.size(), userId);
        return postDtos;
    }
}
