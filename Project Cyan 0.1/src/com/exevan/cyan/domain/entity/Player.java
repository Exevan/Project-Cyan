package com.exevan.cyan.domain.entity;

import com.exevan.cyan.domain.util.Direction;

public class Player extends Actor {
	
	public Player() {
		super(0, 0);
	}
	
	public void move(Direction dir) {
		setX(getX() + dir.getdx());
		setY(getY() + dir.getdy());
		System.out.println("player pos: " + getX() + ", " + getY());
	}
}
