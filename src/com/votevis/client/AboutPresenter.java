package com.votevis.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class AboutPresenter extends Composite {
	
	@UiTemplate("AboutView.ui.xml")
	interface Binder extends UiBinder<Widget, AboutPresenter> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField HTMLPanel personContainer;

	public AboutPresenter () {
	    initWidget(binder.createAndBindUi(this));
	    
	    addPerson("woo", "wooooo");
	}
	
	public void addPerson(String name, String body) {
		AboutPersonPresenter app = new AboutPersonPresenter();
		
		app.setBody(body);
		app.setName(name);
		
		personContainer.add(app);
	}
}
