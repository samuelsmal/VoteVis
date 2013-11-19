package com.votevis.client;

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

public class VisPresenter extends Composite {
	@UiTemplate("VisView.ui.xml")
	interface Binder extends UiBinder<Widget, VisPresenter> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField SpanElement titleSpan;
	//@UiField IFrameElement visIframe;
	
	@UiField CommentPresenter comment;
	
	public VisPresenter () {
	    initWidget(binder.createAndBindUi(this));
	    
	    //visIframe.setSrc("https://www.google.com/fusiontables/embedviz?q=select+col0%3E%3E0+from+1Myt3MFuma1d6QXB9BBQHYMtAiBo4wAsRfbylXZA&amp;viz=MAP&amp;h=false&amp;lat=46.382230029390236&amp;lng=8.653136241529978&amp;t=1&amp;z=8&amp;l=col0%3E%3E0&amp;y=2&amp;tmplt=2&amp;hml=KML");
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