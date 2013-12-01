package com.votevis.client.presenter;

import java.util.Collection;
import java.util.Set;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

// For this class I propose to not use UiBinder since the wanted to dynamically add content to it, 
// which would be troublesome to do with UiBinder.
public class SelectionPopup extends PopupPanel {
	private VisPresenter vis;
	
	private SelectionPopup _this = this;
	
	private VerticalPanel menu = new VerticalPanel();
	
	private HorizontalPanel visTypes = new HorizontalPanel();
	    
    private Label header = new Label("Wählen sie eine Abstimmung aus:");
    
    private Button selectButton;

    private ListBox voteList = new ListBox();
    
    private CheckBox geographic = new CheckBox("Geographisch visualisieren");
    
    private CheckBox tabular = new CheckBox("Tabellarisch visualisieren");
    
    private String selectedVisType = "";
    
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
	        	  String ID = VisPresenter.voteIDs.get(voteList.getValue(voteList.getSelectedIndex()));
	        	  String voteTitle = voteList.getValue(voteList.getSelectedIndex());
	        	  VisPresenter.setVisualisation(ID, voteTitle, selectedVisType);
	          	_this.hide();
	          }
	        });
	      
	      geographic.addClickHandler(new ClickHandler(){
	    	  public void onClick(ClickEvent event) {
	    		 geographic.setChecked(true);
	    		 tabular.setChecked(false);
	        	 selectedVisType = "geo";
	          }	    	  
	    	  
	      });
	      
	      tabular.addClickHandler(new ClickHandler(){
	    	  public void onClick(ClickEvent event) {
	    		 tabular.setChecked(true);
	    		 geographic.setChecked(false);
	        	 selectedVisType = "tab";
	          }	    	  
	    	  
	      });
	      
	      voteList.setSize("300px", "200px");
	      selectButton.setSize("300px", "200px");
	     
	      menu.setSize("300px", "200px");
	      
	      
	      Set<String> votes = VisPresenter.voteIDs.keySet();
	      for(String vote : votes){
	    	  voteList.addItem(vote);
	      }
	     
	     
	      menu.add(header);
	      menu.add(voteList);
	      menu.add(selectButton);
	      visTypes.add(geographic);
	      visTypes.add(tabular);
	      menu.add(visTypes);
	  
	      setWidget(menu);
	      
	      
	      this.addStyleName("selectionpopup");
	}
	
}
