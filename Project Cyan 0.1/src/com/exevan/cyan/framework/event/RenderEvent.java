package com.exevan.cyan.framework.event;

import java.awt.Graphics;

public class RenderEvent extends Event {

	public static final int TYPE = 3;
	
	public RenderEvent(Graphics graphics) {
		super(TYPE);
		this.graphics = graphics;
	}
	
	private Graphics graphics;

	public Graphics getGraphics() {
		return graphics;
	}

}
