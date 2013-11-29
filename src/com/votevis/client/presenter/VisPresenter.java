package com.votevis.client.presenter;


import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
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
	
	@UiField static SpanElement titleSpan;
	
	@UiField static CommentPresenter comment;
	
	@UiField static DivElement bodyDiv;

	
	// Initial geographic visualisation url, links to ID 565
	private static String geoUrl = "https://www.google.com/fusiontables/embedviz?q=select+col5+from+10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk+where+col0+%3D+565&viz=MAP&h=false&lat=46.8&lng=8.3&t=1&z=8&l=col5&y=2&tmplt=2&hml=KML";
	
	// Initial tabular visualistation url, links to ID 570
	private static String tabUrl = "https://www.google.com/fusiontables/embedviz?viz=GVIZ&t=TABLE&q=select+col2%2C+col3%2C+col4%2C+col7%2C+col8%2C+col9%2C+col10%2C+col11%2C+col12%2C+col13+from+10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk+where+col0+%3D+570&containerId=gviz_canvas";
	
	public static HashMap<String, String> voteIDs = new HashMap<String, String>(); //Key: name of vote, Value: ID of vote
	
	public VisPresenter () {

		initalizeVotes();
		
		initWidget(binder.createAndBindUi(this));
		
		bodyDiv.setInnerHTML("<iframe style=\"overflow:hidden;height:100%;width:100%\" height=\"100%\" width=\"100%\">Keine Abstimmung ausgew‰hlt</iframe>" );
	}
	
	
	private void initalizeVotes() {
		voteIDs.put("Volksinitiative vom 07.07.2011 'Volkswahl des Bundesrates", "570");
		voteIDs.put("Volksinitiative vom 18.05.2010 'Schutz vor Passivrauchen", "565");
		voteIDs.put("Volksinitiative vom 26.06.2009 '6 Wochen Ferien f√ºr alle", "557");
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
	
	
	// Only one of the bools is true
	public static void setVisualisation(String ID, String voteTitle, boolean geographicSelected, boolean tabularSelected){
		if(geographicSelected){
			geoUrl = "https://www.google.com/fusiontables/embedviz?q=select+col5+from+10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk+where+col0+%3D+"+ID+"&viz=MAP&h=false&lat=46.8&lng=8.3&t=1&z=8&l=col5&y=2&tmplt=2&hml=KML";
			bodyDiv.setInnerHTML("<iframe src =" + geoUrl + " style=\"overflow:hidden;height:100%;width:100%\" height=\"100%\" width=\"100%\"></iframe>" );
		}

		if(tabularSelected){
			

			tabUrl = "https://www.google.com/fusiontables/embedviz?viz=GVIZ&t=TABLE&q=select+col2%2C+col3%2C+col4%2C+col7%2C+col8%2C+col9%2C+col10%2C+col11%2C+col12%2C+col13+from+10UWQ4DYtmmS1_aaArraatZSGA_6ml9TGwa7FLMk+where+col0+%3D+"+ID+"&containerId=gviz_canvas";
			bodyDiv.setInnerHTML("<iframe src =" + tabUrl + " style=\"overflow:hidden;height:100%;width:100%\" height=\100%\" width=\"100%\"></iframe>" );
		}
		titleSpan.setInnerText(voteTitle);
	}
}

