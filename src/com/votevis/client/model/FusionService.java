package com.votevis.client.model;

import com.google.gwt.http.client.*;

public class FusionService  {
	
	private String fusionTableUrl = "https://www.googleapis.com/fusiontables/v1/query?sql=";
	private String voteBaseTableId = "10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk";
	private String browserKey = "AIzaSyCcjQlwAbsCCZenYYbFXoTE13QEM5rLw7A";
	private RequestBuilder builder;
	private String responseText;
	
	public String getTable() {
		String query = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT * FROM 10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk&key=AIzaSyCcjQlwAbsCCZenYYbFXoTE13QEM5rLw7A";
		builder = new RequestBuilder(RequestBuilder.GET, URL.encode(query));
		
		try {
		  Request request = builder.sendRequest(null, new RequestCallback() {
		    public void onError(Request request, Throwable exception) {
		       responseText = "Couldn't connect to server (could be timeout, SOP violation, etc.)";
		    }

		    public void onResponseReceived(Request request, Response response) {
		      if (200 == response.getStatusCode()) {
		    	responseText = response.getText();
		      } else {
		        responseText = response.getStatusText();
		      }
		    }
		  });
		} catch (RequestException e) {
		  // Couldn't connect to server
			responseText = "Couldn't connect to server";
		}
		
		return responseText;
	}
}
