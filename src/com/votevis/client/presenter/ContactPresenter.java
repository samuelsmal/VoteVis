package com.votevis.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class ContactPresenter extends Composite {

	// Ignore this warning. It is correct.
	@UiTemplate("com.votevis.client.view.ContactView.ui.xml")
	interface Binder extends UiBinder<HTMLPanel, ContactPresenter> {}
	private static final Binder binder = GWT.create(Binder.class);

	public ContactPresenter() {
		initWidget(binder.createAndBindUi(this));
	}
}
