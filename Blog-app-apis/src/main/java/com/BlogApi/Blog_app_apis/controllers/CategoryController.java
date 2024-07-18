package com.BlogApi.Blog_app_apis.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApi.Blog_app_apis.payloads.ApiResponse;
import com.BlogApi.Blog_app_apis.payloads.CategoryDto;
import com.BlogApi.Blog_app_apis.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
	    logger.info("Creating category: {}", categoryDto);
	    CategoryDto createdCategory = categoryService.createCategoryDto(categoryDto);
	    logger.info("Created category: {}", createdCategory);
	    return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {
	    logger.info("Updating category with ID {}: {}", categoryId, categoryDto);
	    CategoryDto updatedCategory = categoryService.updateCategoryDto(categoryDto, categoryId);
	    logger.info("Updated category: {}", updatedCategory);
	    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
	    logger.info("Deleting category with ID: {}", categoryId);
	    categoryService.deleteCategoryDto(categoryId);
	    logger.info("Category deleted successfully");
	    return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully", true), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
	    logger.info("Fetching all categories");
	    List<CategoryDto> categories = categoryService.getAllCategories();
	    logger.info("Fetched {} categories", categories.size());
	    return ResponseEntity.ok(categories);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId) {
	    logger.info("Fetching category with ID: {}", categoryId);
	    CategoryDto category = categoryService.getCategory(categoryId);
	    logger.info("Fetched category: {}", category);
	    return ResponseEntity.ok(category);
	}
}
