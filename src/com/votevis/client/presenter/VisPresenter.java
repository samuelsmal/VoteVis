package com.votevis.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class VisPresenter extends Composite {
	// Ignore this warning. It is correct.
	@UiTemplate("com.votevis.client.view.VisView.ui.xml")
	interface Binder extends UiBinder<Widget, VisPresenter> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField SpanElement titleSpan;
	
	@UiField CommentPresenter comment;
	
	public VisPresenter () {
	    initWidget(binder.createAndBindUi(this));
	    
		String query = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT * FROM 10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk&key=AIzaSyCcjQlwAbsCCZenYYbFXoTE13QEM5rLw7A";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(query));
		
		try {
		  @SuppressWarnings("unused")
		Request request = builder.sendRequest(null, new RequestCallback() {
		    public void onError(Request request, Throwable exception) {
		    	setCommentBody("Couldn't connect to server (could be timeout, SOP violation, etc.)");
		    }

		    public void onResponseReceived(Request request, Response response) {
		      if (200 == response.getStatusCode()) {
		    	  setCommentBody(response.getText());
		      } else {
		    	  setCommentBody(response.getStatusText());
		      }
		    }
		  });
		} catch (RequestException e) {
		  // Couldn't connect to server
			setCommentBody("Couldn't connect to server");
		}
	}
	
	public void setTitle(String title) {
		titleSpan.setInnerText(title);
	}
	
	public void setCommentTitle (String s) {
		comment.titleSpan.setInnerText(s);
	}
	
	public void setCommentBody (String s) {
		comment.bodyDiv.setInnerHTML(s);
	}
}