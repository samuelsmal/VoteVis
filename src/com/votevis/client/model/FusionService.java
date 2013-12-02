package com.votevis.client.model;

import com.google.gwt.core.client.JsonUtils;
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
		    @Override
			public void onError(Request request, Throwable exception) {
		       responseText = "Couldn't connect to server (could be timeout, SOP violation, etc.)";
		    }

		    @Override
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
		
	private String getKey() {
		return "&key=" + browserKey;
	}
	
//	public Request getVotes() {
//		String url = fusionTableUrl + "SELECT ID, Title FROM " + voteBaseTableId + getKey();
//		builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
//		Request request;
//		
//		try {
//			request = builder.sendRequest(null, new RequestCallback() {
//			    @Override
//				public void onError(Request request, Throwable exception) {
//			       // TODO: Throw some exception...
//			    }
//	
//			    @Override
//				public void onResponseReceived(Request request, Response response) {
//			      if (200 == response.getStatusCode()) {
////			    	  kvd.initWithResponse(JsonUtils.safeEval(response.getText()).rows);
//			      } else {
//			        responseText = response.getStatusText();
//			      }
//			    }
//			  });
//		} catch (RequestException e) {
//		  // Couldn't connect to server
//			responseText = "Couldn't connect to server";
//		}
//		
//		return request;
//	}
}
