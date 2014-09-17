package com.exevan.cyan.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

public class Renderer {
	
	int x, y;
	int width, height;
	
	public Renderer(int width, int height) {
		this.x = 0;
		this.y = 0;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.width = screen.width;
		this.height = screen.height;
	}
	
	public void updateSreenPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void drawGrid(Graphics g) {
		int dx = (x % CELL_SIZE) - CELL_SIZE;
		int dy = (y % CELL_SIZE) - CELL_SIZE;
		g.setColor(Color.GREEN);
		for(int i = 0; i <= width; i += CELL_SIZE) {
			for(int j = 0; j <= height; j += CELL_SIZE) {
				g.drawRect(i + dx, j + dy, width, height);
			}
		}
	}
	
	
	public static final int CELL_SIZE = 40;
}
