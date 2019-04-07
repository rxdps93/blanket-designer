package com.dom.bd.template;

import java.io.Serializable;

import javafx.scene.paint.Color;

public class BlanketCell implements Serializable {

	private static final long serialVersionUID = 1L;
	// Whole Square / Half Left
	private double red;
	private double green;
	private double blue;
	
	// Half Square Right
	private double redAlt;
	private double greenAlt;
	private double blueAlt;
	
	private CellType type;
	
	public BlanketCell(Color mainColor, Color altColor, CellType type) {
		setColor(mainColor);
		setAltColor(altColor);
		this.type = type;
	}
	
	public CellType getType() {
		return this.type;
	}
	
	public void setType(CellType type) {
		this.type = type;
	}
	
	public void setColor(Color color) {
		this.red = color.getRed();
		this.green = color.getGreen();
		this.blue = color.getBlue();
	}
	
	public void setAltColor(Color color) {
		this.redAlt = color.getRed();
		this.greenAlt = color.getGreen();
		this.blueAlt = color.getBlue();
	}
	
	public Color getColor() {
		return Color.color(this.red, this.green, this.blue);
	}
	
	public Color getAltColor() {
		return Color.color(this.redAlt, this.greenAlt, this.blueAlt);
	}
	
	public void swapColors() {
		Color temp = this.getColor();
		this.setColor(this.getAltColor());
		this.setAltColor(temp);
	}

}
