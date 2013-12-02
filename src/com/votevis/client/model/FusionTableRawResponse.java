package com.votevis.client.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class FusionTableRawResponse extends JavaScriptObject {
	// Overlay types always have protected, zero-arg ctors
	protected FusionTableRawResponse() { }
	
	// Typically, methods on overlay types are JSNI
	public final native JsArray<Vote> getRows() /*-{ return this.rows; }-*/;
}
