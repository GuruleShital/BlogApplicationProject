package com.BlogApi.Blog_app_apis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApi.Blog_app_apis.entity.Category;
import com.BlogApi.Blog_app_apis.entity.Post;
import com.BlogApi.Blog_app_apis.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
			List<Post> findByUser(User user);
			
			
			List<Post> findByCategory(Category category);

}
