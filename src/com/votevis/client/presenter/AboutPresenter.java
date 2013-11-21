package com.votevis.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class AboutPresenter extends Composite {
	// Ignore this warning. It is correct.
	@UiTemplate("com.votevis.client.view.AboutView.ui.xml")
	interface Binder extends UiBinder<Widget, AboutPresenter> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField HTMLPanel personContainer;

	public AboutPresenter () {
	    initWidget(binder.createAndBindUi(this));

	}
}
