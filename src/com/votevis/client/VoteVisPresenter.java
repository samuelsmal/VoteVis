package com.votevis.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.place.shared.Place;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class VoteVisPresenter implements EntryPoint {
	
	@UiTemplate("VoteVisView.ui.xml")
	interface Binder extends UiBinder<HTMLPanel, VoteVisPresenter> {}
	private static final Binder binder = GWT.create(Binder.class);

	@UiField NavPresenter nav;
	@UiField HTMLPanel content;
	
	private AboutPresenter about;
	private VisPresenter vis;
	
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		HTMLPanel outer = binder.createAndBindUi(this);
		
		nav = new NavPresenter(this);
		
		vis = new VisPresenter();
		
		content.add(vis);
		
		// Gives me a weird error:
		// 		[ERROR] [votevis] - Unable to find type 'com.votevis.client.AboutPersonPanel_BinderImpl.Template'
//		AboutPersonPanel app = new AboutPersonPanel();
		
		Window.enableScrolling(false);
	    Window.setMargin("0px");

		RootLayoutPanel root = RootLayoutPanel.get();
		root.add(outer);
		root.forceLayout();
	}
	
	public void goToAboutPage () {
		content.clear();
		
		if (about == null) {
			about = new AboutPresenter();
		}
		
		content.add(about);
	}
	
	public void goToVisPage () {
		content.clear();
		
		if (vis == null) {
			vis = new VisPresenter();
		}
		
		content.add(vis);
	}
}
