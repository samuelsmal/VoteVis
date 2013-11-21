package com.votevis.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.votevis.client.VoteVisPresenter.Binder;

public class ContactPresenter extends Composite {

	@UiTemplate("ContactView.ui.xml")
	interface Binder extends UiBinder<HTMLPanel, ContactPresenter> {}
	private static final Binder binder = GWT.create(Binder.class);

	public ContactPresenter() {
		initWidget(binder.createAndBindUi(this));
	}
}
