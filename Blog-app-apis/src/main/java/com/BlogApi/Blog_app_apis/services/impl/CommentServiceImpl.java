package com.BlogApi.Blog_app_apis.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApi.Blog_app_apis.entity.Comment;
import com.BlogApi.Blog_app_apis.entity.Post;
import com.BlogApi.Blog_app_apis.exceptions.ResourceNotFoundException;
import com.BlogApi.Blog_app_apis.payloads.CommentDto;
import com.BlogApi.Blog_app_apis.repository.CommentRepo;
import com.BlogApi.Blog_app_apis.repository.PostRepo;
import com.BlogApi.Blog_app_apis.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

		Comment comment = this.modelMapper.map(commentDto, Comment.class);

		comment.setPost(post);

		Comment saveComment = this.commentRepo.save(comment);

		return this.modelMapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));

		this.commentRepo.delete(comment);
	}
}