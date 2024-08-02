package com.BlogApi.Blog_app_apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApi.Blog_app_apis.entity.Comment;

public interface CommentRepo  extends JpaRepository<Comment, Integer>{

}
