package com.exevan.cyan.domain;

public enum Direction {
	
	UP(0, -1),
	DOWN(0, 1),
	LEFT(-1, 0),
	RIGHT(1, 0);
	
	private int dx, dy;
	
	private Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int getdx() {
		return dx;
	}

	public int getdy() {
		return dy;
	}
	
	
}
