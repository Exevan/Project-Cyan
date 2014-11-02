package com.exevan.cyan.domain.entity;

import java.awt.Graphics2D;

import com.exevan.cyan.domain.util.Direction;
import com.exevan.cyan.reference.Reference;
import com.exevan.cyan.ui.rendering.Renderer;

public class Player extends Actor {
	
	public Player() {
		super(0, 0);
	}
	
	public void move(Direction dir) {
		setX(getX() + dir.getdx());
		setY(getY() + dir.getdy());
		System.out.println("player pos: " + getX() + ", " + getY());
	}

	@Override
	public void draw(Graphics2D g) {
		int x = pos.getX() * Renderer.CELL_SIZE;
		int y = pos.getY() * Renderer.CELL_SIZE;
		g.drawImage(Reference.player, x, y, null);
	}
}
