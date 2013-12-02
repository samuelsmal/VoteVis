package com.votevis.client.model;

import com.google.gwt.core.client.JavaScriptObject;

public class Vote extends JavaScriptObject {
	// Overlay types always have protected, zero-arg ctors
	protected Vote() { }
	
	// Typically, methods on overlay types are JSNI
	public final native String getId() /*-{ return this[0]; }-*/;
	public final native String getTitel()  /*-{ return this[1];  }-*/;
}
