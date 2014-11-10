package com.exevan.cyan.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ContextBox extends JPanel {

	private static final long serialVersionUID = 1L;

	public ContextBox() {
		this.setBackground(Color.GREEN);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Helvetica", 1, 32));
		g.drawString("CONTEXT BOX", 10, 40);
	}
}