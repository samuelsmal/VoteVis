package com.votevis.client.presenter;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.http.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.votevis.client.model.FusionTableRawResponse;
import com.votevis.client.model.Vote;

public class VisPresenter extends Composite {
    // ===============================
    // BEGIN UIBINDER VARIABLES
    // ===============================	
	
	// Ignore this warning. It is correct.
	@UiTemplate("com.votevis.client.view.VisView.ui.xml")
	interface Binder extends UiBinder<Widget, VisPresenter> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField static SpanElement titleSpan;
	
	@UiField static CommentPresenter comment;
	
	@UiField static DivElement bodyDiv;
	
    // ===============================
    //	END UIBINDER VARIABLES
    // ===============================	

    // ===============================
    // BEGIN FUSION TABLE VARIABLES
    // ===============================	
	private String fusionTableUrl = "https://www.googleapis.com/fusiontables/v1/query?sql=";
	private String voteBaseTableId = "10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk";
	private String browserKey = "AIzaSyCcjQlwAbsCCZenYYbFXoTE13QEM5rLw7A";
	private RequestBuilder fusionBuilder;
	private Request fusionRequest;
	
	final PopupPanel loadingPopup = new PopupPanel(false, true); 
	    
    // ===============================
    // END FUSION TABLE VARIABLES
    // ===============================
	
   
	// ===============================
    // BEGIN VISUALISATION DATA
    // ===============================	
	
	// Initial geographic visualisation url, links to ID 565
	private String geoUrl = "https://www.google.com/fusiontables/embedviz?q=select+col5+from+10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk+where+col0+%3D+565&viz=MAP&h=false&lat=46.8&lng=8.3&t=1&z=8&l=col5&y=2&tmplt=2&hml=KML&z=6";
	
	// Initial tabular visualistation url, links to ID 570
	private String tabUrl = "https://www.google.com/fusiontables/embedviz?viz=GVIZ&t=TABLE&q=select+col2%2C+col3%2C+col4%2C+col7%2C+col8%2C+col9%2C+col10%2C+col11%2C+col12%2C+col13+from+10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk+where+col0+%3D+570&containerId=gviz_canvas";
	
	private HashMap<String, String> voteIDs = new HashMap<String, String>(); //Key: name of vote, Value: ID of vote

	private HashSet<String> easternCantons = new HashSet<String>();
	
	private HashSet<String> ticino = new HashSet<String>();

	public HashSet<String> getRomandie() {
		return romandie;
	}

	private HashSet<String> centralCantons = new HashSet<String>();
	
	private HashSet<String> zurichRegion = new HashSet<String>();
	
	private HashSet<String> baselRegion = new HashSet<String>();
	
	private HashSet<String> bernRegion = new HashSet<String>();
	
	private HashSet<String> romandie = new HashSet<String>();





	
	// ===============================
    // END VISUALISATION DATA
    // ===============================	
	
	public VisPresenter () {
		loadingPopup.add(new HTML("<div class=\"lp-container\">Loading the FISH</div>"));
		loadingPopup.setGlassEnabled(true); // Enable the glass panel
		loadingPopup.show(); // .hide() is called by the last fusiontable function: initalizeVotes().
		
		InitializeCantons();
		loadingPopup.setStyleName("loadingPopup");

		accessFusionTable();
		
		initWidget(binder.createAndBindUi(this));
		
		bodyDiv.setInnerHTML("<iframe src=\"http://erinsimpson.tv/2012/cool/joke_eel_template.jpg\" style=\"overflow:hidden;height:100%;width:100%\" height=\"100%\" width=\"100%\"></iframe>" );
	}
	
	
    private void InitializeCantons() {
    	easternCantons.add("St. Gallen");
		easternCantons.add("Graubünden");
		easternCantons.add("Thurgau");
		easternCantons.add("Appenzell A.-Rh.");
		easternCantons.add("Appenzell I.-Rh.");
		ticino.add("Tessin");
		centralCantons.add("Glarus");
		centralCantons.add("Uri");
		centralCantons.add("Nidwalden");
		centralCantons.add("Obwalden");
		centralCantons.add("Schwyz");
		centralCantons.add("Luzern");
		centralCantons.add("Zug");
		zurichRegion.add("Zürich");
		zurichRegion.add("Schaffhausen");
		baselRegion.add("Basel-Landschaft");
		baselRegion.add("Basel-Stadt");
		baselRegion.add("Aargau");
		bernRegion.add("Bern");
		bernRegion.add("Solothurn");
		romandie.add("Wallis");
		romandie.add("Genf");
		romandie.add("Waadt");
		romandie.add("Freiburg");
		romandie.add("Neuenburg");
		romandie.add("Jura");
    }

	// ===============================
    // BEGIN FUSION TABLE FUNCTIONS
    // ===============================	
	private String getKey() {
		return "&key=" + browserKey;
	}
	
	public void accessFusionTable() {
		String url = fusionTableUrl + "SELECT ID, Titel FROM " + voteBaseTableId + getKey();
		fusionBuilder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
		
		try {
			fusionRequest = fusionBuilder.sendRequest(null, new RequestCallback() {
			    @Override
				public void onError(Request request, Throwable exception) {
			       // TODO: Throw some exception...
			    }
	
			    @Override
				public void onResponseReceived(Request request, Response response) {
			      if (200 == response.getStatusCode()) {
			    	  initalizeVotes(response.getText());
			      } else {
//			        response.getStatusText();
			      }
			    }
			  });
		} catch (RequestException e) {
		  // Couldn't connect to server
		}
	}
	
	public void initalizeVotes(String response) {
		FusionTableRawResponse ftrr = JsonUtils.safeEval(response);
		JsArray<Vote> votesArray = ftrr.getRows();
		
		for (int i = 0, l = votesArray.length(); i < l; i+=26) {
			voteIDs.put(votesArray.get(i).getTitel(), votesArray.get(i).getId());
		}
		
		loadingPopup.hide();
	}
	
    // ===============================
    // END FUSION TABLE FUNCTIONS
    // ===============================

    // ===============================
    // BEGIN VISUALISATION FUNCTIONS
    // ===============================
	
	@Override
	public void setTitle(String title) {
		titleSpan.setInnerText(title);
	}
	
	public void setCommentTitle (String s) {
		comment.titleSpan.setInnerText(s);
	}
	
	public void setCommentBody (String s) {
		comment.bodyDiv.setInnerHTML(s);
	}
	
	public HashSet<String> getTicino() {
		return ticino;
	}

	public HashSet<String> getCentralCantons() {
		return centralCantons;
	}

	public HashSet<String> getZurichRegion() {
		return zurichRegion;
	}

	public HashSet<String> getBaselRegion() {
		return baselRegion;
	}

	public HashSet<String> getBernRegion() {
		return bernRegion;
	}

	public HashMap<String, String> getVoteIDs() {
		return voteIDs;
	}

	public HashSet<String> getEasternCantons() {
		return easternCantons;
	}

	public void setVisualisation(String ID, String voteTitle, int visSelected, String selectedCantons){
		if(visSelected == 1){
			geoUrl = "https://www.google.com/fusiontables/embedviz?q=select+col5+from+10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk+where+col4+in+"+selectedCantons+"+and+col0+%3D+"+ID+"&viz=MAP&h=false&lat=46.80123670109627&lng=8.08734039843739&t=1&z=8&l=col5&y=2&tmplt=2&hml=KML";
			bodyDiv.setInnerHTML("<iframe src=\"" + geoUrl + "\" style=\"overflow:hidden;height:100%;width:100%\" height=\"100%\" width=\"100%\"></iframe>" );
		}

		if(visSelected == 0){
			tabUrl = "https://www.google.com/fusiontables/embedviz?viz=GVIZ&t=TABLE&q=select+col2%2C+col4%2C+col7%2C+col8%2C+col9%2C+col10%2C+col11%2C+col12%2C+col13+from+10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk+where+col0+%3D+"+ID+"+and+col4+in+"+selectedCantons+"&containerId=googft-gviz-canvas";
			bodyDiv.setInnerHTML("<iframe src=\"" + tabUrl + "\" style=\"overflow:hidden;height:100%;width:100%\" height=\100%\" width=\"100%\"></iframe>" );
		}
		titleSpan.setInnerText(voteTitle);
	}
}	  

	// ===============================
    // END VISUALISATION FUNCTIONS
    // ===============================

