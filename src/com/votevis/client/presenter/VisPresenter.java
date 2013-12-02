package com.votevis.client.presenter;


import java.util.HashMap;

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
	// Ignore this warning. It is correct.
	@UiTemplate("com.votevis.client.view.VisView.ui.xml")
	interface Binder extends UiBinder<Widget, VisPresenter> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField static SpanElement titleSpan;
	
	@UiField static CommentPresenter comment;
	
	@UiField static DivElement bodyDiv;

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
	
	// Initial geographic visualisation url, links to ID 565
	private static String geoUrl = "https://www.google.com/fusiontables/embedviz?q=select+col5+from+10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk+where+col0+%3D+565&viz=MAP&h=false&lat=46.8&lng=8.3&t=1&z=8&l=col5&y=2&tmplt=2&hml=KML";
	
	// Initial tabular visualistation url, links to ID 570
	private static String tabUrl = "https://www.google.com/fusiontables/embedviz?viz=GVIZ&t=TABLE&q=select+col2%2C+col3%2C+col4%2C+col7%2C+col8%2C+col9%2C+col10%2C+col11%2C+col12%2C+col13+from+10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk+where+col0+%3D+570&containerId=gviz_canvas";
	
	public static HashMap<String, String> voteIDs = new HashMap<String, String>(); //Key: name of vote, Value: ID of vote
	
	public VisPresenter () {
		loadingPopup.add(new HTML("<div class=\"lp-container\">LOA<br>d<br><br><em>IIIIIIIII</em>ng the <span class=\"lp-fish\">FISH<span></div>"));
		loadingPopup.setGlassEnabled(true); // Enable the glass panel
		loadingPopup.show(); // .hide() is called by the last fusiontable function: initalizeVotes().
		loadingPopup.setStyleName("loadingPopup");
		
		accessFusionTable();
		
		initWidget(binder.createAndBindUi(this));
		
		bodyDiv.setInnerHTML("<iframe src=\""+geoUrl+"\" style=\"overflow:hidden;height:100%;width:100%\" height=\"100%\" width=\"100%\"></iframe>" );
		
	}
	
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

	public static void setVisualisation(String ID, String voteTitle, int visSelected, String cantonsSelected){
		if(visSelected == 1){
			geoUrl = "https://www.google.com/fusiontables/embedviz?q=select+col5+from+10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk+where+col0+%3D+"+ID+"&viz=MAP&h=false&lat=46.8&lng=8.3&t=1&z=8&l=col5&y=2&tmplt=2&hml=KML";
			bodyDiv.setInnerHTML("<iframe src=\"" + geoUrl + "\" style=\"overflow:hidden;height:100%;width:100%\" height=\"100%\" width=\"100%\"></iframe>" );
		}

		if(visSelected == 0){
			tabUrl = "https://www.google.com/fusiontables/embedviz?viz=GVIZ&t=TABLE&q=select+col2%2C+col3%2C+col4%2C+col7%2C+col8%2C+col9%2C+col10%2C+col11%2C+col12%2C+col13+from+10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk+where+col0+%3D+"+ID+"&containerId=gviz_canvas";
			bodyDiv.setInnerHTML("<iframe src=\"" + tabUrl + "\" style=\"overflow:hidden;height:100%;width:100%\" height=\100%\" width=\"100%\"></iframe>" );
		}
		titleSpan.setInnerText(voteTitle);
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
}

