package com.BlogApi.Blog_app_apis.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@NoArgsConstructor
@Setter
@Getter

public class User {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int userId;
	
	@Column(name="Name", nullable=false,length = 150)
	private String userName;
	
	private String userEmail;
	private String userPassword;
	private String userAbout;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Post>  posts=new ArrayList<>();


}
