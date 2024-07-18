package com.BlogApi.Blog_app_apis.payloads;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

	private Integer categoryId;

	@Size
	private String categoryTitle;

	private String categoryDescription;
}
