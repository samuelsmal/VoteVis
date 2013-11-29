package com.votevis.client.presenter; 


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;


import com.google.gwt.user.client.ui.RootLayoutPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class VoteVisPresenter implements EntryPoint {
	// Ignore this warning. It is correct.
	@UiTemplate("com.votevis.client.view.VoteVisView.ui.xml")
	interface Binder extends UiBinder<HTMLPanel, VoteVisPresenter> {}
	private static final Binder binder = GWT.create(Binder.class);

	@UiField HTMLPanel content;
	@UiField Button homeLink;
	@UiField Button aboutLink;
	@UiField Button contactLink;
	@UiField Button selectionButton;
	
	private AboutPresenter about;
	private VisPresenter vis;
	private SelectionPopup selection;
	private ContactPresenter contact;
	

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		HTMLPanel outer = binder.createAndBindUi(this);
		
		vis = new VisPresenter();
		
		content.add(vis);

		selectionButton.setEnabled(true);
		
		Window.enableScrolling(true);
	    Window.setMargin("0 auto");
	
		RootLayoutPanel root = RootLayoutPanel.get();
		root.add(outer);
		root.forceLayout();
	}

	@UiHandler("homeLink")
	public void handleHome (ClickEvent e) {
		content.clear();
		
		if (vis == null) {
			vis = new VisPresenter();
		}
		
		selectionButton.setEnabled(true);
		content.add(vis);
	}
	
	@UiHandler("aboutLink")
	public void handleAbout (ClickEvent e) {
		content.clear();
		
		if (about == null) {
			about = new AboutPresenter();
		}
		
		selectionButton.setEnabled(false);
		content.add(about);
	}
	
	@UiHandler("contactLink")
	public void handleContact (ClickEvent e) {
		content.clear();
		
		if (contact == null) {
			contact = new ContactPresenter();
		}
		
		selectionButton.setEnabled(false);
		content.add(contact);
	}
	
	@UiHandler("selectionButton")
	public void handleSelection (ClickEvent e) {
		openSelectionDialog();
	}
			
	public void openSelectionDialog() {		
		
		if (selection == null) {

			selection = new SelectionPopup();
		}
		selection.center();
	}
	
}

