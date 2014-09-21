package com.exevan.cyan.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Vector;

import java.awt.event.ActionEvent;

public class Renderer extends Thread {

	int x, y;
	int width, height;

	private Vector<ActionListener> listeners;

	public Renderer(int width, int height) {
		this.x = 0;
		this.y = 0;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.width = screen.width;
		this.height = screen.height;
		this.listeners = new Vector<ActionListener>();
	}

	synchronized public void registerListener(ActionListener listener) {
		listeners.add(listener);
	}

	private void updateUI() {
		synchronized (this) {
			ActionEvent e = new ActionEvent(this, 0, "tick");
			for (ActionListener l : listeners) {
				l.actionPerformed(e);
			}
		}
	}

	@Override
	public void run() {
		super.run();
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
			updateUI();
		}
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
			g.drawLine(i+dx, 0, i+dx, 900);
		}
		for(int j = 0; j <= height + CELL_SIZE; j += CELL_SIZE) {
			g.drawLine(0, j+dy, 1600, j+dy);
		}
	}


	public static final int CELL_SIZE = 40;
}
