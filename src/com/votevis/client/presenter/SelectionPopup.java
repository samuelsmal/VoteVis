package com.votevis.client.presenter;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

// For this class I propose to not use UiBinder since the wanted to dynamically add content to it, 
// which would be troublesome to do with UiBinder.
public class SelectionPopup extends PopupPanel {

	public SelectionPopup() {
	      // PopupPanel's constructor takes 'auto-hide' as its boolean parameter.
	      // If this is set, the panel closes itself automatically when the user
	      // clicks outside of it.
	      super(true);
	
	      // PopupPanel is a SimplePanel, so you have to set it's widget property to
	      // whatever you want its contents to be.
	      // We have to use our own Panel in order to add content to it, since PopupPanel is a SimplePanel and can therefore
	      // only have one Widget.
	      setWidget(new Label("Click outside of this popup to close it"));
	      
	      this.addStyleName("selectionpopup");
	}
}
