package com.exevan.cyan.domain;

public class Player {
	
	private int x, y;
	
	public Player() {
		this.x = 0;
		this.y = 0;
	}
	
	public void move(Direction dir) {
		this.x = x + dir.getdx();
		this.y = y + dir.getdy();
		System.out.println("player pos: " + this.x + ", " + this.y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
