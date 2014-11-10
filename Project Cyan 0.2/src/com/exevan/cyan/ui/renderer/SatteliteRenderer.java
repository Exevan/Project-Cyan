package com.exevan.cyan.ui.renderer;

import java.awt.Color;
import java.awt.Graphics;

import com.exevan.cyan.domain.world.Tile;
import com.exevan.cyan.domain.world.World;

public class SatteliteRenderer extends AbstractRenderer {
	
	@Override
	protected void drawMap(World world, Graphics g, int x, int y, int width, int height) {
		int w = (width / cellSize) + 1;
		int h = (height / cellSize) + 1;
		Tile[][] map = world.getMap();
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				float d = (float) Math.abs(map[x+i][y+j].getHeight());
				d = (d > 1.0f) ? 1.0f : d;
				Color c = null;
				if (d < 0.4f) c = new Color(0, 0, d/0.4f);
				else if (d < 0.42f) c = Color.YELLOW;
				else if (d < 0.7f) c = new Color(0, d/0.7f, 0);
				else c = new Color(d, d, d);
				g.setColor(c); 	
				g.fillRect(i*cellSize, j*cellSize, cellSize, cellSize);
			}
		}
	}
}
