package com.exevan.cyan.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import com.exevan.cyan.framework.dispatch.InputDispatcher;
import com.exevan.cyan.ui.rendering.Renderer;

public class Display extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private InputDispatcher dispatcher;
	
	private Renderer renderer;
	
	public Display() {
		this.renderer = new Renderer(this.getWidth(), this.getHeight());
		renderer.start();
		this.setBackground(Color.BLACK);
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				int x = e.getX() / Renderer.CELL_SIZE;
				int y = e.getY() / Renderer.CELL_SIZE;
				dispatcher.postEvent(new com.exevan.cyan.framework.event.MouseEvent(x, y));
				renderer.updateSreenPosition(e.getX(), e.getY());
			}
		};
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
		this.setIgnoreRepaint(true);
	}
	
	public void setDispatcher(InputDispatcher dispatcher) {
		this.dispatcher = dispatcher;
		this.renderer.setDispatcher(dispatcher);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		renderer.renderScreen(g);
	}

}
