package com.exevan.cyan.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

public class Renderer extends Thread {

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
			g.drawLine(i+dx, 0, i+dx, height);
		}
		for(int j = 0; j <= height + CELL_SIZE; j += CELL_SIZE) {
			g.drawLine(0, j+dy, width, j+dy);
		}
	}


	public static final int CELL_SIZE = 40;
}
