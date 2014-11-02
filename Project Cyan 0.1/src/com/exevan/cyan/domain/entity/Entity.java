package com.exevan.cyan.domain.entity;

import java.awt.Graphics2D;

import com.exevan.cyan.domain.util.Position;
import com.exevan.cyan.framework.event.Event;
import com.exevan.cyan.framework.event.IEventListener;
import com.exevan.cyan.ui.rendering.Drawable;

public abstract class Entity implements IEventListener, Drawable {

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

	@Override
	public void handle(Event e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public abstract void draw(Graphics2D g);

}
