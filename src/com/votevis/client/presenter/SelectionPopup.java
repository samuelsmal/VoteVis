package com.votevis.client.presenter;

import java.util.HashMap;
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
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

// For this class I propose to not use UiBinder since the wanted to dynamically add content to it, 
// which would be troublesome to do with UiBinder.
public class SelectionPopup extends PopupPanel {
    private SelectionPopup _this = this;
	VisPresenter vis;
	
	//=================
	//		Labels
	//=================
    private Label header = new Label("Wählen sie eine Abstimmung");
    private Label cantonsHeader = new Label("Wählen sie Kantone");
    private Label visTypeHeader = new Label("Wählen sie einen Visualisierungstyp");
	
    //=================
    //		Panels
    //=================
	private DockPanel menu = new DockPanel();
	private VerticalPanel voteSelectPanel = new VerticalPanel();
	private VerticalPanel cantonSelectPanel = new VerticalPanel();
	private HorizontalPanel visTypes = new HorizontalPanel();
	
	//==================
	//		Buttons
	//==================
	private Button selectButton = new Button("Abstimmung visualisieren");
	private RadioButton tabularButton = new RadioButton("visType", "Tabellarisch");
	private RadioButton geographicButton = new RadioButton("visType", "Geographisch");
	
	//=================
	//		Boxes
	//=================
	private Set<CheckBox> regionsBoxes = new HashSet<CheckBox>();
	private ListBox voteList;
	
	//Variables
	private int visSelected = 1; // Geographic = 1, Tabular = 0
	private HashMap<String, HashSet<String>> regions = new HashMap<String, HashSet<String>>();

    

 
    
    
    

    
	public SelectionPopup(final VisPresenter vis) {
		  // PopupPanel's constructor takes 'auto-hide' as its boolean parameter.
	      // If this is set, the panel closes itself automatically when the user
	      // clicks outside of it.
	      super(true);
	      
	      
	      
	      // PopupPanel is a SimplePanel, so you have to set it's widget property to
	      // whatever you want its contents to be.
	      // We have to use our own Panel in order to add content to it, since PopupPanel is a SimplePanel and can therefore
	      // only have one Widget.
	      
	      this.vis = vis;
	      
	    	
		    //=======================================================================
		    // Initializations/Populations
		    //=====================================================================
		      // Add available votes to the Listbox
		      voteList = new ListBox();
		      Set<String> votes = vis.getVoteIDs().keySet();
		      for(String vote : votes){
		    	  voteList.addItem(vote);
		      }
		      
		      // Add checkboxes for regions and the sets from VisPresenter
		      regionsBoxes.add(new CheckBox("Ostschweiz"));
		      regions.put("Ostschweiz", vis.getEasternCantons());
		      regionsBoxes.add(new CheckBox("Tessin"));
		      regions.put("Tessin", vis.getTicino());
		      regionsBoxes.add(new CheckBox("Zentralschweiz"));
		      regions.put("Zentralschweiz", vis.getCentralCantons());
		      regionsBoxes.add(new CheckBox("Züribiet"));
		      regions.put("Züribiet", vis.getZurichRegion());
		      regionsBoxes.add(new CheckBox("Baselbiet"));
		      regions.put("Baselbiet", vis.getBaselRegion());
		      regionsBoxes.add(new CheckBox("Bernerland"));
		      regions.put("Bernerland", vis.getBernRegion());
		      regionsBoxes.add(new CheckBox("Romandie"));
		      regions.put("Romandie", vis.getRomandie());

		      for(CheckBox box : regionsBoxes){
		    	  box.setValue(true);
		      }
			     
		      
	      // 	=================================================
	      //	ClickHandlers
	      //	=================================================
	     
	      // Add a Clickhandler to the selectButton to call setVisualisation and change the vote and/or visualisation and select/deselect cantons
	      selectButton = new Button("Abstimmung visualisieren", new ClickHandler() {
	    	  @Override
			public void onClick(ClickEvent event) {
	        	  String ID = vis.getVoteIDs().get(voteList.getValue(voteList.getSelectedIndex()));
	        	  String voteTitle = voteList.getValue(voteList.getSelectedIndex());
		    	  String selectedCantons = "";

	        	  
	        	  for(CheckBox box : regionsBoxes){
	        		  if(box.getValue()){
	        			  //Get the corresponding set of cantons out of the Map
	        			  for(String canton : regions.get(box.getText())){
	        				  selectedCantons += "'" + canton + "'" + "%2C+";
	        			  }
	        		  }
	        	  }
	        	  // I need to remove the HTML representation of a comma at the end and a "+" of the string and add parentheses at the beginning and end
	        	  String HTMLComma = "%2C+";
	        	  selectedCantons = "(" + selectedCantons.substring(0, selectedCantons.length() - HTMLComma.length()) + ")";
	        	 
	        	  // Now we can pass the string directly to setVisualisation and insert it into the URL
	        	   
	        	  vis.setVisualisation(ID, voteTitle, visSelected, selectedCantons);
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

	    //========================================================================
	

	      //===================================
		  // Style Settings
		  //===================================
	      menu.setSize("500px", "50px");
	      voteSelectPanel.setSize("400px", "500px");
	      header.setSize("100px", "20px");
	      cantonsHeader.setSize("100px", "20px");
	      voteList.setSize("300px", "20px");
	      visTypes.setSize("200px", "20px");
	      selectButton.setSize("200px", "80px");

	      cantonSelectPanel.setSize("100px", "100px");
	     
	      
	      //Create a new Panel, add Widgets to it and set the widget in Popup
	      visTypes.add(geographicButton);
	      visTypes.add(tabularButton);
	      
	      voteSelectPanel.add(header);
	      voteSelectPanel.add(voteList);
	      voteSelectPanel.add(visTypeHeader);
	      voteSelectPanel.add(visTypes);
	      voteSelectPanel.add(selectButton);
	      
	      for(CheckBox box : regionsBoxes){
	    	  cantonSelectPanel.add(box);
	      }
	      menu.add(voteSelectPanel, DockPanel.WEST);
	      menu.add(cantonSelectPanel, DockPanel.EAST);

	      setWidget(menu);
	      
	      
	      this.addStyleName("selectionpopup");
	}
	
}
