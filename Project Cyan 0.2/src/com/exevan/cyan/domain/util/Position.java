package com.exevan.cyan.domain.util;

public class Position {

	private int x, y;

	public Position(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public int getX() {
		return x;
	}

	public boolean canHaveAsX(int x) {
		return (x >= 0) && (x < Integer.MAX_VALUE);
	}

	public void setX(int x) {
		if(canHaveAsX(x))
			this.x = x;
	}

	public int getY() {
		return y;
	}

	public boolean canHaveAsY(int y) {
		return (y >= 0) && (y < Integer.MAX_VALUE);
	}

	public void setY(int y) {
		if (canHaveAsY(y))
			this.y = y;
	}
}
