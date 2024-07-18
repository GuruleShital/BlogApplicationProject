package com.BlogApi.Blog_app_apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApi.Blog_app_apis.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
	

}
