package com.exevan.cyan.domain.world;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import com.exevan.cyan.domain.entity.Entity;
import com.exevan.cyan.domain.entity.Player;
import com.exevan.cyan.domain.util.Direction;
import com.exevan.cyan.domain.util.Position;
import com.exevan.cyan.framework.event.Event;
import com.exevan.cyan.framework.event.IEventListener;
import com.exevan.cyan.framework.event.KeyEvent;
import com.exevan.cyan.framework.event.RenderEvent;
import com.exevan.cyan.ui.rendering.Drawable;

public class World implements IEventListener, Drawable {

	private Map<Position, Entity> entities;
	private Player player;

	public World() {
		this.player = new Player();
		this.entities = new HashMap<Position, Entity>();
		entities.put(player.getPos(), player);
	}

	@Override
	public void handle(Event e) {
		if (e.getType() == KeyEvent.TYPE)
			this.handleKeyEvent((KeyEvent) e);
		if (e.getType() == RenderEvent.TYPE) {
			Graphics g = ((RenderEvent) e).getGraphics();
			synchronized (g) {
				this.draw((Graphics2D) g);
			}
		}
	}

	private void handleKeyEvent(KeyEvent e) {
		switch (e.getKey()) {
		case java.awt.event.KeyEvent.VK_Z:
			movePlayer(Direction.UP);
			break;
		case java.awt.event.KeyEvent.VK_Q:
			movePlayer(Direction.LEFT);
			break;
		case java.awt.event.KeyEvent.VK_S:
			movePlayer(Direction.DOWN);
			break;
		case java.awt.event.KeyEvent.VK_D:
			movePlayer(Direction.RIGHT);
			break;
		default:
			break;
		}

	}
	
	private void movePlayer(Direction dir) {
		entities.remove(player.getPos(), player);
		player.move(dir);
		entities.put(player.getPos(), player);
	}

	@Override
	public void draw(Graphics2D g) {
		for(Entity entity : entities.values())
			entity.draw(g);
	}
}
