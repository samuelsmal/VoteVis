package com.votevis.client.presenter;

import java.util.Collection;
import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

// For this class I propose to not use UiBinder since the wanted to dynamically add content to it, 
// which would be troublesome to do with UiBinder.
public class SelectionPopup extends PopupPanel {
	VisPresenter vis;
	
	SelectionPopup _this = this;
	
    private VerticalPanel menu = new VerticalPanel();
    
    private Label description = new Label("Wählen sie eine Abstimmung aus:");
    
    Button selectButton;

    ListBox voteList = new ListBox();
    
	public SelectionPopup(VisPresenter vp) {
		  // PopupPanel's constructor takes 'auto-hide' as its boolean parameter.
	      // If this is set, the panel closes itself automatically when the user
	      // clicks outside of it.
	      super(true);
	      
	      vis = vp;
	      
	      // PopupPanel is a SimplePanel, so you have to set it's widget property to
	      // whatever you want its contents to be.
	      // We have to use our own Panel in order to add content to it, since PopupPanel is a SimplePanel and can therefore
	      // only have one Widget.
	      
	      selectButton = new Button("Abstimmung auswählen", new ClickHandler() {
	          public void onClick(ClickEvent event) {
	          	VisPresenter.setVisualisation(VisPresenter.voteIDs.get(voteList.getValue(voteList.getSelectedIndex())));
	          	_this.hide();
	          }
	        });
	     
	      menu.setSize("400px", "200px");
	      
	      
	      Set<String> votes = VisPresenter.voteIDs.keySet();
	      for(String vote : votes){
	    	  voteList.addItem(vote);
	      }
	      
	     
	      
	      menu.add(description);
	      menu.add(voteList);
	      menu.add(selectButton);
	  
	      setWidget(menu);
	      
	      
	      this.addStyleName("selectionpopup");
	}
	
}
