package com.votevis.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class SelectionDialog extends DialogBox {

	private static SelectionDialogUiBinder uiBinder = GWT
			.create(SelectionDialogUiBinder.class);

	interface SelectionDialogUiBinder extends UiBinder<Widget, SelectionDialog> {
	}

	public SelectionDialog() {
		 setWidget(uiBinder.createAndBindUi(this));
	}

}
