package com.dom.bd.utilities;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javafx.scene.paint.Color;

public enum Global {

	OBJECTS;
	
	private final Map<String, Color> colorMap = new TreeMap<>();
	
	private Global() {
		this.colorMap.put("Black", Color.BLACK);
		this.colorMap.put("Dark Gray", Color.DARKGRAY);
		this.colorMap.put("Gray", Color.GRAY);
		this.colorMap.put("Blue", Color.BLUE);
		this.colorMap.put("Slate Gray", Color.SLATEGRAY);
		this.colorMap.put("Purple", Color.PURPLE);
		this.colorMap.put("Green", Color.GREEN);
		this.colorMap.put("Dark Olive Green", Color.DARKOLIVEGREEN);
		this.colorMap.put("Dark Red", Color.DARKRED);
		this.colorMap.put("Saddle Brown", Color.SADDLEBROWN);
		this.colorMap.put("White", Color.WHITE);
		this.colorMap.put("Light Gray", Color.LIGHTGRAY);
		this.colorMap.put("Cadet Blue", Color.CADETBLUE);
		this.colorMap.put("Light Sky Blue", Color.LIGHTSKYBLUE);
		this.colorMap.put("Cyan", Color.CYAN);
		this.colorMap.put("Dark Orange", Color.DARKORANGE);
		this.colorMap.put("Yellow", Color.YELLOW);
		this.colorMap.put("Lawn Green", Color.LAWNGREEN);
		this.colorMap.put("Red", Color.RED);
		this.colorMap.put("Sandy Brown", Color.SANDYBROWN);
	}
	
	public Color[] getColors() {
		Color[] colors = new Color[this.colorMap.values().size()];
		return this.colorMap.values().toArray(colors);
	}
	
	public String[] getColorNames() {
		String[] colorNames = new String[this.colorMap.keySet().size()];
		return this.colorMap.keySet().toArray(colorNames);
	}
	
	public String getColorNameByColor(Color color) {
		if (this.colorMap.containsValue(color)) {
			for (Entry<String, Color> mapping : this.colorMap.entrySet()) {
				if (mapping.getValue().equals(color))
					return mapping.getKey();
			}
		}
		return "UNKNOWN";
	}
	
	public Color getColorByName(String name) {
		if (this.colorMap.containsKey(name))
			return this.colorMap.get(name);
		else
			return null;
	}
}
