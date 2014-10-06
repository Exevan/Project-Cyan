package com.exevan.cyan.domain.world;

import com.exevan.cyan.domain.entity.Player;
import com.exevan.cyan.domain.util.Direction;
import com.exevan.cyan.framework.event.Event;
import com.exevan.cyan.framework.event.IEventListener;
import com.exevan.cyan.framework.event.KeyEvent;

public class World implements IEventListener {

	private EntityTree entities;
	private Player player;

	public World() {
		this.player = new Player();
	}

	@Override
	public void handle(Event e) {
		if (e.getType() == KeyEvent.TYPE)
			this.handleKeyEvent((KeyEvent) e);	
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
		//remove player from world
		player.move(dir);
		entities.insert(player);
	}
}
