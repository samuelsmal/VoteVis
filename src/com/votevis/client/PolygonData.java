package com.votevis.client;

import java.util.HashMap;
import java.util.Map;


// Polygondata for every Canton
public class PolygonData {
	private Map<String, String> geometry;
	private Map<String, Integer> geometryVertexCount;
	
	public PolygonData(){
		geometry = new HashMap<String, String>();
		geometryVertexCount = new HashMap<String, Integer>();
	
	}
}
