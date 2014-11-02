package com.exevan.cyan.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import com.exevan.cyan.domain.world.World;
import com.exevan.cyan.ui.renderer.Renderer;

public class Display extends JPanel {

	private static final long serialVersionUID = 1L;
	private World world;
	private Renderer renderer;
	
	public Display(World world) {
		this.world = world;
		this.renderer = new Renderer();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension(screen.width, screen.height - CyanUI.DEFUALT_BOTTOM_HEIGHT));
		this.setBackground(Color.BLACK);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		renderer.drawWorld(world, g);
	}
}