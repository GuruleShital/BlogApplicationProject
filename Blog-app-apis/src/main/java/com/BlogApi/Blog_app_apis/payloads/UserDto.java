package com.BlogApi.Blog_app_apis.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//to transfer data
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int userId;
	
	@NotEmpty
	@Size(min=4,message="UserName must be min 4 chracter")
	private String userName;
	
	@Email(message="Email not valid")
	private String userEmail;
	
	@NotEmpty
	@Size(min=3,max=8,message="Enter valid password")
	private String userPassword;
	
	@NotEmpty(message="About must insert")
	private String userAbout;

}
