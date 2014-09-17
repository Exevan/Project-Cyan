package com.exevan.cyan.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Display extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Renderer renderer;
	
	public Display() {
		this.renderer = new Renderer(this.getWidth(), this.getHeight());
		this.setBackground(Color.BLACK);
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				renderer.updateSreenPosition(e.getX(), e.getY());
				repaint();
			}
		};
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		renderer.drawGrid(g);
	}

}
