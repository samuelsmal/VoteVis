package com.votevis.client.presenter;

import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;

// For this class I propose to not use UiBinder since the wanted to dynamically add content to it, 
// which would be troublesome to do with UiBinder.
public class SelectionPopup extends PopupPanel {
	
	
	
	private SelectionPopup _this = this;
    
	private DockPanel menu = new DockPanel();

	private HorizontalPanel visTypes = new HorizontalPanel();
	    
    private Label header = new Label("Wählen sie eine Abstimmung");
    
    private Label visTypeHeader = new Label("Wählen sie einen Visualisierungstyp");
    
    private Button selectButton;

    private ListBox voteList = new ListBox();
    
    private RadioButton geographicButton = new RadioButton("visType", "Geographisch");
    
    private RadioButton tabularButton = new RadioButton("visType", "Tabellarisch");
    
    int visSelected = 1; // Geographic = 1, Tabular = 0

    
	public SelectionPopup() {
		  // PopupPanel's constructor takes 'auto-hide' as its boolean parameter.
	      // If this is set, the panel closes itself automatically when the user
	      // clicks outside of it.
	      super(true);
	      
	      
	      
	      // PopupPanel is a SimplePanel, so you have to set it's widget property to
	      // whatever you want its contents to be.
	      // We have to use our own Panel in order to add content to it, since PopupPanel is a SimplePanel and can therefore
	      // only have one Widget.
	      

	      // Add a Clickhandler to the selectButton to call setVisualisation and change the vote and/or visualisation
	      selectButton = new Button("Abstimmung visualisieren", new ClickHandler() {

	          @Override
			public void onClick(ClickEvent event) {
	        	  String ID = VisPresenter.voteIDs.get(voteList.getValue(voteList.getSelectedIndex()));
	        	  String voteTitle = voteList.getValue(voteList.getSelectedIndex());
	        	  VisPresenter.setVisualisation(ID, voteTitle, visSelected);
	          	_this.hide();
	          }
	        });
	      

	      geographicButton.setValue(true);
	      tabularButton.setValue(false);
	      geographicButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				visSelected = 1;
			}
	      });
	      
	      tabularButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				visSelected = 0;
			}
	    	  
	      });
	      voteList.setSize("400px", "20");
		  selectButton.setSize("400px", "30px");

	      menu.setSize("400", "150");

	      // Set visualisation type bool values to Button values
	      
	      
	      // Add available votes to the Listbox
	      Set<String> votes = VisPresenter.voteIDs.keySet();
	      for(String vote : votes){
	    	  voteList.addItem(vote);
	      }
	  

	      menu.setSize("400px", "200px");
	      header.setSize("200px", "20px");
	      voteList.setSize("400px", "20px");
	      visTypes.setSize("200px", "20px");
	      selectButton.setSize("200px", "80px");

	      
	      visTypes.add(geographicButton);
	      visTypes.add(tabularButton);
	      
	      //Create a new Panellayout, add Widgets to it and set the widget in Popup
	      menu.add(header, DockPanel.NORTH);
	      menu.add(voteList, DockPanel.NORTH);
	      menu.add(visTypeHeader, DockPanel.NORTH);
	      menu.add(visTypes, DockPanel.NORTH);
	      menu.add(selectButton, DockPanel.SOUTH);
	    

	      setWidget(menu);
	      
	      
	      this.addStyleName("selectionpopup");
	}
	
}
