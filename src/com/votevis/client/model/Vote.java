package com.votevis.client.model;

import com.google.gwt.core.client.JavaScriptObject;

public class Vote extends JavaScriptObject {
	// Overlay types always have protected, zero-arg ctors
	protected Vote() { }
	
	// Typically, methods on overlay types are JSNI
	public final native String getFirstName() /*-{ return this.FirstName; }-*/;
	public final native String getLastName()  /*-{ return this.LastName;  }-*/;
	
	// Note, though, that methods aren't required to be JSNI
	public final String getFullName() {
		return getFirstName() + " " + getLastName();
	}
}
