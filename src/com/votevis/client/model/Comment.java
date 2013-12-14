package com.votevis.client.model;

import com.google.gwt.core.client.JavaScriptObject;

// Without the JavaScriptObject-Extension local storage wouldn't work.
public class Comment extends JavaScriptObject{	
	protected Comment() {}
	
	public static native Comment create(String a, String d, String c) /*-{
		return {author: a, date: d, comment: c};
	}-*/;
	
	public final native String getAuthor() /*-{ return this.author; }-*/;
	public final native String getDate() /*-{ return this.date; }-*/;
	public final native String getComment() /*-{ return this.comment; }-*/;
	
	public final native void setAuthor(String a) /*-{ this.author = a; }-*/;
	public final native void setDate(String d) /*-{ this.date = d; }-*/;
	public final native void setComment(String s) /*-{ this.comment = s; }-*/;
}
