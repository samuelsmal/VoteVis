package com.votevis.client.model;

import java.util.ArrayList;
import java.util.List;

public class CommentBase {

	private List<Comment> comments;
	private List<Picture> pictures;
	
	public CommentBase() {
		this.comments = new ArrayList<Comment>();
		this.pictures = new ArrayList<Picture>();
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	

	
}
