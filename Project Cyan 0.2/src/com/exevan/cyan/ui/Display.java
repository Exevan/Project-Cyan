package com.exevan.cyan.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import com.exevan.cyan.domain.world.World;
import com.exevan.cyan.ui.renderer.AbstractRenderer;
import com.exevan.cyan.ui.renderer.SatteliteRenderer;

public class Display extends JPanel {

	private static final long serialVersionUID = 1L;
	private World world;
	private AbstractRenderer renderer;
	private int worldX, worldY;

	public Display(World world) {
		this.world = world;
		this.renderer = new SatteliteRenderer();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension(screen.width, screen.height - CyanUI.DEFUALT_BOTTOM_HEIGHT));
		this.setBackground(Color.BLACK);
	}


	public int getWorldX() {
		return worldX;
	}

	public boolean canHaveAsWorldX(int x) {
		return (x >= 0) && (x < world.getWidth() - (this.getWidth() / AbstractRenderer.getCellSize()));
	}

	public void setWorldX(int x) {
		if (canHaveAsWorldX(x))
			this.worldX = x;
	}

	public int getWorldY() {
		return worldY;
	}

	public boolean canHaveAsWorldY(int y) {
		return (y >= 0) && (y < world.getHeight() - (this.getHeight() / AbstractRenderer.getCellSize()));
	}

	public void setWorldY(int y) {
		if (canHaveAsWorldY(y))
			this.worldY = y;
	}

	public int getWorldScale() {
		return AbstractRenderer.getCellSize();
	}

	public void setWorldScale(int worldScale) {
		AbstractRenderer.setCellSize(worldScale);
	}


	@Override
	public void paint(Graphics g) {
		super.paint(g);
		renderer.drawWorld(world, g, worldX, worldY, this.getWidth(), this.getHeight());
	}
}