package com.votevis.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

// TODO 1.) Fix this class. Path seems wrong. See VoteVis.java

public class AboutPersonPresenter extends Composite {
	
	@UiTemplate("AboutPersonView.ui.xml")
	interface Binder extends UiBinder<Widget, AboutPersonPresenter> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField SpanElement name;
	@UiField DivElement body;
	
	public AboutPersonPresenter () {
	    initWidget(binder.createAndBindUi(this));
	}
	
	public void setName(String n) {
		name.setInnerText(n);
	}
	
	public void setBody(String b) {
		body.setInnerText(b);
	}
}
