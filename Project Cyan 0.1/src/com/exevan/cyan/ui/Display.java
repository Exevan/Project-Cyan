package com.exevan.cyan.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Display extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Renderer renderer;
	
	public Display() {
		this.renderer = new Renderer();
		this.setBackground(Color.BLACK);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		renderer.drawGrid(this.getWidth(), this.getHeight(), g);
	}

}
