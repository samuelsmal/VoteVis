package com.votevis.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;


public class NavPresenter extends Composite {
	
	@UiTemplate("NavView.ui.xml")
	interface Binder extends UiBinder<Widget, NavPresenter> { }
	private static final Binder binder = GWT.create(Binder.class);
	
	private VoteVisPresenter callbackObj;
	
	@UiField Button homeLink;
	@UiField Button aboutLink;
	@UiField Button contactLink;
	@UiField Button selectionButton;
	
	
	public NavPresenter () {
	    initWidget(binder.createAndBindUi(this));
	}
	
	public NavPresenter (VoteVisPresenter vvp) {
		setCallbackObj(vvp);
	    initWidget(binder.createAndBindUi(this));
	}
	
	public void setCallbackObj (VoteVisPresenter obj) {
		this.callbackObj = obj;
	}
	
	@UiHandler("homeLink")
	public void handleHome (ClickEvent e) {
//		foo.setInnerText("home clicked");
//		callbackObj.callbackdiv.setInnerText("super");
	}
	
	@UiHandler("aboutLink")
	public void handleAbout (ClickEvent e) {
		
	}
	
	@UiHandler("contactLink")
	public void handleContact (ClickEvent e) {
//		callbackObj.goToAboutPage();
//		foo.setInnerText("contact clicked");
		Window.alert("about@votevis.com");
		
	}
	
	@UiHandler("selectionButton")
	public void handleSelection (ClickEvent e) {

	}
}
