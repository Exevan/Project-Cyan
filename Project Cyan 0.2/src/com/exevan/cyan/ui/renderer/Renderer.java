package com.exevan.cyan.ui.renderer;

import java.awt.Color;
import java.awt.Graphics;

import com.exevan.cyan.domain.world.World;

public class Renderer {

	public void drawWorld(World world, Graphics g) {
		drawMap3(world.getMap(), g);
	}
	
	private void drawMap(double[][] map, Graphics g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				float d = (float) Math.abs(map[i][j]);
				d = (d > 1.0f) ? 1.0f : d;
				Color c = new Color(d, d, d);
				g.setColor(c); 	
				g.fillRect(i, j, 1, 1);
			}
		}
	}
	
	private void drawMap2(double[][] map, Graphics g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				float d = (float) Math.abs(map[i][j]);
				d = (d > 1.0f) ? 1.0f : d;
				Color c = Color.getHSBColor(d, 1.0f, 0.5f);
				g.setColor(c); 	
				g.fillRect(i, j, 1, 1);
			}
		}
	}
	
	private void drawMap3(double[][] map, Graphics g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				float d = (float) Math.abs(map[i][j]);
				d = (d > 1.0f) ? 1.0f : d;
				Color c = null;
				if(d < 0.4f) c = new Color(0, 0, d/0.4f);
				else if (d < 0.45f) c = Color.YELLOW;
				else if (d < 0.7f) c = new Color(0, d/0.7f, 0);
				else c = new Color(d, d, d);
				g.setColor(c); 	
				g.fillRect(i, j, 1, 1);
			}
		}
	}
	
}
