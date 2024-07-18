package com.BlogApi.Blog_app_apis.services;

import java.util.List;

import com.BlogApi.Blog_app_apis.payloads.CategoryDto;

public interface CategoryService {
	
	//create
	public CategoryDto createCategoryDto(CategoryDto categoryDto);
	
	//update
	public CategoryDto updateCategoryDto(CategoryDto categoryDto,Integer categoryId);
	
	//Delete
	public void deleteCategoryDto(Integer categoryId);
	
	//getSingle Record
	public CategoryDto getCategory(Integer categoryId);
	
	//getAllRecord
	public List<CategoryDto> getAllCategories();
	
	

}
