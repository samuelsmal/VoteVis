package com.votevis.client.presenter;


import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.IFrameElement;
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
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class VisPresenter extends Composite {
	// Ignore this warning. It is correct.
	@UiTemplate("com.votevis.client.view.VisView.ui.xml")
	interface Binder extends UiBinder<Widget, VisPresenter> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField SpanElement titleSpan;
	
	@UiField static CommentPresenter comment;
	
	@UiField static DivElement bodyDiv;

	
	private static String url = "https://www.google.com/fusiontables/embedviz?q=select+col5+from+10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk+where+col0+%3D+565&viz=MAP&h=false&lat=46.77302748609018&lng=8.74102692187489&t=1&z=8&l=col5&y=2&tmplt=2&hml=KML";
	
	public static HashMap<String, String> voteIDs = new HashMap<String, String>();
	
	public VisPresenter () {

		initalizeVotes();
		
		initWidget(binder.createAndBindUi(this));
	
	    /*
	    String options = "ID, Title, Wahlergebnis, Datum, Kanton, Stimmberechtigte, 'Abgegebene Stimmen', Stimmbeteiligung, 'Ja Stimmen', 'Nein Stimmen', 'Ja Stimmen in Prozent', 'Nein Stimmen in Prozent'";

	    initWidget(binder.createAndBindUi(this));
	    
	    String options = "ID, Titel, Wahlergebnis, Datum, Kanton, Stimmberechtigte, 'Abgegebene Stimmen', Stimmbeteiligung, 'Ja Stimmen', 'Nein Stimmen', 'Ja Stimmen in Prozent', 'Nein Stimmen in Prozent'";

	    String urlFirstPart = "https://www.googleapis.com/fusiontables/v1/query?sql=";
		String query = "SELECT ID FROM ";
		String urlLastPart = "10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk&key=AIzaSyCcjQlwAbsCCZenYYbFXoTE13QEM5rLw7A";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(urlFirstPart + query + urlLastPart));
		
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
		*/
		setTitle("Abstimmung");
		bodyDiv.setInnerHTML("<iframe src =" + url + "</iframe>" );
	}
	
	private void initalizeVotes() {
		voteIDs.put("Volksinitiative vom 07.07.2011 'Volkswahl des Bundesrates", "570");
		voteIDs.put("Volksinitiative vom 18.05.2010 'Schutz vor Passivrauchen", "565");
		voteIDs.put("Volksinitiative vom 26.06.2009 '6 Wochen Ferien für alle", "557");
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
	
	public static void setVisualisation(String ID){
		url = "https://www.google.com/fusiontables/embedviz?q=select+col5+from+10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk+where+col0+%3D+"+ID+"&viz=MAP&h=false&lat=46.77302748609018&lng=8.74102692187489&t=1&z=8&l=col5&y=2&tmplt=2&hml=KML";
		bodyDiv.setInnerHTML("<iframe src =" + url + " style=\"overflow:hidden;height:100%;width:100%\" height=\"100%\" width=\"100%\"></iframe>" );
	}
}

