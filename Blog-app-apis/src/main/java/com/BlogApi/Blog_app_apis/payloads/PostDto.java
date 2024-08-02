package com.BlogApi.Blog_app_apis.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.BlogApi.Blog_app_apis.entity.Category;
import com.BlogApi.Blog_app_apis.entity.Comment;
import com.BlogApi.Blog_app_apis.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class PostDto {

	private String title;

	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDto category;

	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
}
