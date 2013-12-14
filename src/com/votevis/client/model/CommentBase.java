package com.votevis.client.model;

import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.storage.client.Storage;

import java.util.ArrayList;
import java.util.List;

public class CommentBase {
    // ===============================
    // BEGIN LOCAL STORAGE VARIABLES
    // ===============================	
	private Storage commentStore = null;

	private List<Comment> comments;
	private List<Picture> pictures;
	
	public CommentBase() {
		this.comments = new ArrayList<Comment>();
		this.pictures = new ArrayList<Picture>();
		
		commentStore = Storage.getLocalStorageIfSupported();
		
		if (commentStore != null) {
			for (int i = 0; i < commentStore.getLength(); ++i) {
				String key = commentStore.key(i); // Should be what? Name? Doesn't matter...
				String value = commentStore.getItem(key);
				
				Comment c = JsonUtils.safeEval(value);
				this.comments.add(c);
			}
		}

	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment c) {
		if (commentStore != null) {
			commentStore.setItem("Comments." + commentStore.getLength(), c.toString());
		}
		this.comments.add(c);
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
}
