package com.exevan.cyan.domain.entity;

import com.exevan.cyan.domain.util.Position;

public abstract class Entity {

	private Position pos;

	public Position getPos() {
		return pos;
	}

	public boolean canHaveAsPos(Position pos) {
		return (pos != null);
	}

	public void setPos(Position pos) {
		if (canHaveAsPos(pos))
			this.pos = pos;
	}	
}
