package com.exevan.cyan.ui.renderer;

import java.awt.Color;
import java.awt.Graphics;

import com.exevan.cyan.domain.world.World;

public abstract class AbstractRenderer {
	
	public final void drawWorld(World world, Graphics g, int x, int y, int width, int height) {
		drawMap(world, g, x, y, width, height);
		drawGrid(g, width, height);
	}
	
	protected abstract void drawMap(World world, Graphics g, int x, int y, int width, int height);
	
	protected void drawGrid(Graphics g, int width, int height) {
		if (AbstractRenderer.getCellSize() < 6)
			return;
		g.setColor(new Color(25, 25, 25));
		for (int i = 0; (i+1)*cellSize < width; i++) {
			g.drawLine(i*cellSize, 0, i*cellSize, height);
		}
		for (int j = 0; (j+1)*cellSize < width; j++) {
			g.drawLine(0, j*cellSize, width, j*cellSize);
		}
	}
	
	protected static int cellSize = 16;
	
	public static int getCellSize() {
		return cellSize;
	}

	public static boolean isValidCellSize(int cellSize) {
		return (cellSize > 1) && (cellSize < 64);
	}

	public static void setCellSize(int cellSize) {
		if (isValidCellSize(cellSize))
			AbstractRenderer.cellSize = cellSize;
	}
}