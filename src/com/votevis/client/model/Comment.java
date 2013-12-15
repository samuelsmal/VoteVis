package com.votevis.client.model;

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;

// Without the JavaScriptObject-Extension local storage wouldn't work.
public class Comment extends JavaScriptObject{	
	protected Comment() {}
		
	public final native String getAuthor() /*-{ return this.author; }-*/;
	public final native String getDate() /*-{ return this.date; }-*/;
	public final native String getText() /*-{ return this.text; }-*/;
	
	public final native void setAuthor(String a) /*-{ this.author = a; }-*/;
	public final native void setDate(String d) /*-{ this.date = d; }-*/;
	public final native void setText(String s) /*-{ this.text = s; }-*/;
	
	public final native String getJSONString() /*-{
	  return "{\"author\":\"" + this.author + "\", \"date\":\"" + this.date + "\", \"text\":\"" + this.text + "\"}";
	}-*/;
}
