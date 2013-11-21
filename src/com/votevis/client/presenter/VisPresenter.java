package com.votevis.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.votevis.client.model.FusionService;

public class VisPresenter extends Composite {
	@UiTemplate("../view/VisView.ui.xml")
	interface Binder extends UiBinder<Widget, VisPresenter> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField SpanElement titleSpan;
	//@UiField IFrameElement visIframe;
	
	@UiField CommentPresenter comment;
	
	public VisPresenter () {
	    initWidget(binder.createAndBindUi(this));
	    
	    FusionService fs = new FusionService();
	    setCommentBody(fs.getTable());
	}
	
	public void setTitle(String title) {
		titleSpan.setInnerText(title);
	}
	
	public void setCommentTitle (String s) {
		comment.titleSpan.setInnerText(s);
	}
	
	public void setCommentBody (String s) {
		comment.bodyDiv.setInnerHTML(s);
	}
}