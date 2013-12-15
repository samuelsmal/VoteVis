package com.votevis.client.model;

import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;

import java.util.ArrayList;
import java.util.List;

public class CommentBase {
    // ===============================
    //       BEGIN LOCAL STORAGE 
    // ===============================	
	public Storage commentStore = null;

	private List<Picture> pictures;
	
	public CommentBase() {
		this.pictures = new ArrayList<Picture>();
		
		commentStore = Storage.getLocalStorageIfSupported();
		
		if (commentStore == null) {
			Window.alert("Your browser is too old! Go get a new one!");
		}
	}
	
	public void addComment(Comment c) {
		// Saves the comment on the local store.
		if (commentStore != null) {
			commentStore.setItem("Comments." + commentStore.getLength(), c.getJSONString());
		}
	}
	
	public Comment getCommentAt(int i) {
		Comment c = JsonUtils.safeEval(commentStore.getItem(commentStore.key(i)));
		return c;
	}

    // ===============================
    //        END LOCAL STORAGE 
    // ===============================		
	
	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
}
