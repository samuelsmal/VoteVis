
package com.votevis.client.presenter;

import java.util.ArrayList;
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
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.votevis.client.model.*;


public class CommentPresenter extends Composite {
	// Ignore this warning. It is correct.
	@UiTemplate("com.votevis.client.view.CommentView.ui.xml")
	interface Binder extends UiBinder<Widget, CommentPresenter> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField HTMLPanel contentet;
	@UiField SpanElement titleSpan;
	@UiField DivElement bodyDiv;
	//@UiField DivElement oldComments;
	@UiField Button addComment;
	@UiField Button resetComment;
	@UiField RichTextArea textInputField;
	@UiField TextBox author;
	@UiField DecoratorPanel dpanel;
	
	CommentBase cBase;
	private FlexTable flexComment = new FlexTable();
		
	public CommentPresenter() {
	    initWidget(binder.createAndBindUi(this));
	    cBase = new CommentBase();
	    author.setText("Name");
	    textInputField.setText("Geben Sie hier ihren Kommentar ein.");
	    flexComment.setWidth("700px");
		dpanel.add(flexComment);
		Comment c = new Comment();
		c.setDate(new Date());
		c.setAuthor("Commentard");
		c.setComment("Comment collision. Imagine a search engine that simply removed the top 1 million most popular web sites from its index. What would you discover? millionshort.com");
		cBase.setComments(new ArrayList<Comment>());
		cBase.getComments().add(c);
		updateComments();
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
	
	
	@UiHandler("addComment")
	public void addComment (ClickEvent e) {
		
		if (cBase == null) {
			cBase = new CommentBase();
		}
		Comment c = new Comment();
		c.setAuthor(author.getText());
		c.setDate(new Date());
		c.setComment(textInputField.getText());
		
		cBase.getComments().add(c);
		updateComments();
	}
	
	@UiHandler("resetComment")
	public void resetComment (ClickEvent e) {
		cBase.setComments(new ArrayList<Comment>());
		flexComment.clear();
		dpanel.clear();
		dpanel.add(flexComment);
		updateComments();
	}
	
	public void updateComments(){
		int idx = 0;
		for (Comment c : cBase.getComments()){
			
		flexComment.setWidget(idx++, 0, new Label("Kommentiert am "+c.getDate().toString()+" von "+c.getAuthor()+":"));
		flexComment.setWidget(idx++, 0, new Label(c.getComment()));
		flexComment.setWidget(idx++, 0, new Label("-----------------------------------------------------------------"));
		}
	}
}
