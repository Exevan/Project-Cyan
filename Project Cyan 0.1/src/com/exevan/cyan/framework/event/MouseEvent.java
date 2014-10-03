package com.exevan.cyan.framework.event;

public class MouseEvent extends Event {

	public static final int TYPE = 3;
	
	private int x, y;
	
	public MouseEvent(int x, int y) {
		super(TYPE);
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
