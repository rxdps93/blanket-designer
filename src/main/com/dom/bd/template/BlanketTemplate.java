package com.dom.bd.template;

import java.io.Serializable;

import javafx.scene.paint.Color;

public class BlanketTemplate implements Serializable {

	private static final long serialVersionUID = 1L;
	private BlanketCell[][] canvas;
	private String templateName;
	private final int width;
	private final int height;
	private int cellSize = 20;
	
	public BlanketTemplate(int height, int width, String name, Color initialColor) {
		this.canvas = new BlanketCell[height][width];
		this.templateName = name;
		this.width = width;
		this.height = height;
		
		for (int rows = 0; rows < height; rows++) {
			for (int cols = 0; cols < width; cols++) {
				this.canvas[rows][cols] = new BlanketCell(initialColor, 
						(initialColor.equals(Color.WHITE) ? Color.BLACK : Color.WHITE), CellType.WHOLE);
			}
		}
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public BlanketCell[][] getCanvas() {
		
		return this.canvas;
	}
	
	public BlanketCell getCell(int row, int col) {
		
		return this.canvas[row][col];
	}
	
	public void setName(String name) {
		this.templateName = name;
	}
	
	public String getName() {
		return this.templateName;
	}
	
	public int getCellSize() {
		return this.cellSize;
	}
	
	/**
	 * Resizes the cells. Minimum value is 5, maximum value is 25, default value is 15.
	 * 
	 * @param cellSize an integer value that must be between 5 and 25 (inclusive)
	 */
	public void setCellSize(int cellSize) {
		this.cellSize = cellSize < 5 ? 5 : cellSize > 25 ? 25 : cellSize;
	}
	
	public void zoomIn() {
		setCellSize(getCellSize() + 5);
	}
	
	public void zoomOut() {
		setCellSize(getCellSize() - 5);
	}
}
