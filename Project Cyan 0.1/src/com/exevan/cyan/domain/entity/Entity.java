package com.exevan.cyan.domain.entity;

import com.exevan.cyan.domain.util.Position;

public class Entity {

	protected Position pos;
	
	public Entity(int x, int y) {
		this.pos = new Position(x, y);
	}
	
	public Position getPos() {
		return this.pos;
	}

	public int getX() {
		return pos.getX();
	}

	public void setX(int x) {
		pos.setX(x);
	}

	public int getY() {
		return pos.getY();
	}

	public void setY(int y) {
		pos.setY(y);
	}

}
