package com.votevis.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class CommentPresenter extends Composite {
	// Ignore this warning. It is correct.
	@UiTemplate("com.votevis.client.view.CommentView.ui.xml")
	interface Binder extends UiBinder<Widget, CommentPresenter> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField SpanElement titleSpan;
	@UiField DivElement bodyDiv;
	
	
	public CommentPresenter() {
	    initWidget(binder.createAndBindUi(this));
	}
	
	
	public void setTitle (String title) {
		titleSpan.setInnerText(title);
	}
	
	public void setBody (String text) {
		bodyDiv.setInnerText(text);
	}
}