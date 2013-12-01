package com.votevis.client.presenter;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

// For this class I propose to not use UiBinder since the wanted to dynamically add content to it, 
// which would be troublesome to do with UiBinder.
public class SelectionPopup extends PopupPanel {
	
	
	
	private SelectionPopup _this = this;
    
	private DockPanel menu = new DockPanel();
	
	private VerticalPanel voteSelectPanel = new VerticalPanel();
	
	private VerticalPanel cantonSelectPanel = new VerticalPanel();
	
	private HorizontalPanel cantonSelectButtonPanel = new HorizontalPanel();
	
	private Button cantonSelectAllButton = new Button("Alle Kantone anzeigen");
	
	private Button cantonDeselectAllButton = new Button("Keine Kantone anzeigen");
	
	private Set<CheckBox> cantonSet = new HashSet<CheckBox>();
	
	private HorizontalPanel visTypes = new HorizontalPanel();
	    
    private Label header = new Label("Wählen sie eine Abstimmung");
    
    private Label cantonsHeader = new Label("Wählen sie Kantone");
    
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
	      

	      // Add a Clickhandler to the selectButton to call setVisualisation and change the vote and/or visualisation and select/deselect cantons
	      selectButton = new Button("Abstimmung visualisieren", new ClickHandler() {

	          @Override
			public void onClick(ClickEvent event) {
	        	  String ID = VisPresenter.voteIDs.get(voteList.getValue(voteList.getSelectedIndex()));
	        	  String voteTitle = voteList.getValue(voteList.getSelectedIndex());
	        	  String selectedCantons = "";
	        	  // Get selected cantons and add the names of cantons to String
	        	  for(CheckBox cb : cantonSet){
	        		  if(cb.getValue() == true)
	        			  selectedCantons += cb.getText();
	        	  }
	        	  String cantonsSelected = "";
				VisPresenter.setVisualisation(ID, voteTitle, visSelected, cantonsSelected );
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

	   
	      
	      // Add available votes to the Listbox
	      Set<String> votes = VisPresenter.voteIDs.keySet();
	      for(String vote : votes){
	    	  voteList.addItem(vote);
	      }
	      
	    

	      menu.setSize("500px", "500px");
	      voteSelectPanel.setSize("300px", "400px");
	      header.setSize("100px", "20px");
	      cantonsHeader.setSize("100px", "20px");
	      voteList.setSize("300px", "20px");
	      visTypes.setSize("200px", "20px");
	      selectButton.setSize("200px", "80px");

	      cantonSelectPanel.setSize("100px", "100px");
	      visTypes.add(geographicButton);
	      visTypes.add(tabularButton);
	      
	      //Create a new Panel, add Widgets to it and set the widget in Popup
	      voteSelectPanel.add(header);
	      voteSelectPanel.add(voteList);
	      voteSelectPanel.add(visTypeHeader);
	      voteSelectPanel.add(visTypes);
	      voteSelectPanel.add(selectButton);
	      menu.add(voteSelectPanel, DockPanel.WEST);
	      cantonSelectButtonPanel.add(cantonSelectAllButton);
	      cantonSelectButtonPanel.add(cantonDeselectAllButton);
	      cantonSelectPanel.add(cantonSelectButtonPanel);
	     
	      for(int i = 0; i < 26; ++i){
	    	  CheckBox cb = new CheckBox("Kanton");
	    	  cantonSelectPanel.add(cb);
	    	  cantonSet.add(cb);
	      }
	      
	      cantonSelectAllButton.addClickHandler(new ClickHandler(){
	    	@Override
			public void onClick(ClickEvent event) {
				for(CheckBox cb : cantonSet){
					cb.setValue(true);
				}
	    	}
			});
	    	
	    	cantonDeselectAllButton.addClickHandler(new ClickHandler(){
	 	    	@Override
	 			public void onClick(ClickEvent event) {
	 				for(CheckBox cb : cantonSet){
	 					cb.setValue(false);
	 				}
	 			}
	    	  
	      });
	      menu.add(cantonSelectPanel, DockPanel.EAST);

	      setWidget(menu);
	      
	      
	      this.addStyleName("selectionpopup");
	}
	
}
