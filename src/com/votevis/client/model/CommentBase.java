package com.votevis.client.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.storage.client.Storage;

import java.util.ArrayList;
import java.util.List;

public class CommentBase {
    // ===============================
    // BEGIN LOCAL STORAGE VARIABLES
    // ===============================	
	public Storage commentStore = null;

	public JsArray<Comment> comments;
	private List<Picture> pictures;
	
	public CommentBase() {
		this.pictures = new ArrayList<Picture>();
		
		commentStore = Storage.getLocalStorageIfSupported();
		
		if (commentStore != null) {
			for (int i = 0; i < commentStore.getLength(); ++i) {
				String key = commentStore.key(i);
				String value = commentStore.getItem(key);
				
				Comment c = JsonUtils.safeEval(value);
				/*
				comments.set(comments.length(), c);
				comments.setLength(comments.length() + 1);*/
				
			}
		}

	}

	public JsArray<Comment> getComments() {
		return comments;
	}
	
	public void addComment(Comment c) {
		// Saves the comment on the local store.
		if (commentStore != null) {
			commentStore.setItem("Comments." + commentStore.getLength(), c.getJSONString());
		}
		/*
		comments.set(comments.length(), c);
		comments.setLength(comments.length() + 1);*/
	}
	
	public Comment getCommentAt(int i) {
		Comment c = JsonUtils.safeEval(commentStore.getItem(commentStore.key(i)));
		return c;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
}
