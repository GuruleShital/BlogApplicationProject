package com.BlogApi.Blog_app_apis.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApi.Blog_app_apis.entity.Category;
import com.BlogApi.Blog_app_apis.exceptions.ResourceNotFoundException;
import com.BlogApi.Blog_app_apis.payloads.CategoryDto;
import com.BlogApi.Blog_app_apis.repository.CategoryRepo;
import com.BlogApi.Blog_app_apis.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategoryDto(CategoryDto categoryDto) {
        logger.info("Creating category with data: {}", categoryDto);
        Category mappedCategory = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepo.save(mappedCategory);
        logger.info("Category created: {}", savedCategory);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategoryDto(CategoryDto categoryDto, Integer categoryId) {
        logger.info("Updating category with ID: {}", categoryId);
        Category existingCategory = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        existingCategory.setCategoryTitle(categoryDto.getCategoryTitle());
        existingCategory.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = categoryRepo.save(existingCategory);
        logger.info("Category updated: {}", updatedCategory);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategoryDto(Integer categoryId) {
        logger.info("Deleting category with ID: {}", categoryId);
        Category existingCategory = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryRepo.delete(existingCategory);
        logger.info("Category deleted successfully");
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        logger.info("Fetching category with ID: {}", categoryId);
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        logger.info("Fetching all categories");
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
        logger.info("Fetched {} categories", categoryDtos.size());
        return categoryDtos;
    }
}
