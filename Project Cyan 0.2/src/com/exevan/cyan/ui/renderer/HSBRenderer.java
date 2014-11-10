package com.exevan.cyan.ui.renderer;

import java.awt.Color;
import java.awt.Graphics;

import com.exevan.cyan.domain.world.Tile;
import com.exevan.cyan.domain.world.World;

public class HSBRenderer extends AbstractRenderer {

	@Override
	protected void drawMap(World world, Graphics g, int x, int y, int width, int height) {
		Tile[][] map = world.getMap();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				float d = (float) Math.abs(map[i][j].getHeight());
				d = (d > 1.0f) ? 1.0f : d;
				Color c = Color.getHSBColor(d, 1.0f, 0.5f);
				g.setColor(c); 	
				g.fillRect(i*cellSize, j*cellSize, cellSize, cellSize);
			}
		}		
	}
}