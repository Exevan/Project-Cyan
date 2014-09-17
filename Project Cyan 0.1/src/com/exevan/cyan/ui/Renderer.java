package com.exevan.cyan.ui;

import java.awt.Color;
import java.awt.Graphics;

public class Renderer {
	
	public void drawGrid(int width, int height, Graphics g) {
		g.setColor(Color.GREEN);
		for(int i = 0; i <= width; i += CELL_SIZE) {
			for(int j = 0; j <= height; j += CELL_SIZE) {
				g.drawRect(i, j, width, height);
			}
		}
	}
	
	
	public static final int CELL_SIZE = 40;
}
