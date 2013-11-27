package com.votevis.client.presenter;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;
import com.votevis.client.model.*;


public class CommentPresenter extends Composite {
	// Ignore this warning. It is correct.
	@UiTemplate("com.votevis.client.view.CommentView.ui.xml")
	interface Binder extends UiBinder<Widget, CommentPresenter> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField SpanElement titleSpan;
	@UiField DivElement bodyDiv;
	@UiField DivElement oldComments;
	@UiField Button addComment;
	@UiField Button resetComment;
	@UiField RichTextArea textInputField; 
	
	CommentBase cBase;
	
	public CommentPresenter() {
	    initWidget(binder.createAndBindUi(this));
	    cBase = new CommentBase();
	}
	
	public void setTitle (String title) {
		titleSpan.setInnerText(title);
	}
	
	public void setBody (String body) {
		bodyDiv.setInnerText(body);
	}
	
	
    public HasHTML getEnteredText() { 
            return this.textInputField; 
    } 

    public Widget asWidget() { 
            return this; 
    } 
	
	public void setOldComments (String body) {
		oldComments.setInnerText(body);
	}
	
	@UiHandler("addComment")
	public void addComment (ClickEvent e) {
		bodyDiv.setInnerText("bla");
	}
	
	@UiHandler("resetComment")
	public void resetComment (ClickEvent e) {
		bodyDiv.setInnerText("blabla");
	}
	
	/*
	public void getCommentText (String body) {
		oldComments.setInnerText(body);
	}
	
	@UiHandler("commentLink")
	public void handleComment(ClickEvent e) {
		//content.clear();
		/*
		if (cBase == null) {
			CommentBase cBase = new CommentBase();
		}
		Comment c = new Comment();
		//c.setAuthor(author);
		c.setDate(new Date());
		//c.setComment(comment);
		
		cBase.getComments().add(c);
		//content.add(vis);
		 
	}
*/
	
}