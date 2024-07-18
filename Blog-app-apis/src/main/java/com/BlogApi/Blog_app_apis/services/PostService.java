package com.BlogApi.Blog_app_apis.services;

import java.util.List;

import com.BlogApi.Blog_app_apis.entity.Post;
import com.BlogApi.Blog_app_apis.payloads.PostDto;

public interface PostService {
	
	
	//Create
	PostDto createPost(PostDto postDto ,Integer userId,Integer categoryId);
	
	//Update
	Post updatePost(PostDto postDto,Integer postId);
	
	//Delete
	void deletePost(Integer postId);

	//GetAllPost
	List<PostDto> getAllpost();

	//GetSinglePost
	PostDto getPostById(Integer postId);
	
	//All PostBy  category
	List<PostDto> getAllPostByCategoryId(Integer categoryId);
	
	//All post by User
	List<PostDto> getAllPostByUserId(Integer userId);
	
	//get Search Post
	List<Post> searchPosts(String keyword);
	
}
