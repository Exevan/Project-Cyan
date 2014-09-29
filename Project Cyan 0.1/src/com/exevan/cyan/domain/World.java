package com.exevan.cyan.domain;

import com.exevan.cyan.framework.event.Event;
import com.exevan.cyan.framework.event.IEventListener;
import com.exevan.cyan.framework.event.KeyEvent;

public class World implements IEventListener {

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
			player.move(Direction.UP);
			break;
		case java.awt.event.KeyEvent.VK_Q:
			player.move(Direction.LEFT);
			break;
		case java.awt.event.KeyEvent.VK_S:
			player.move(Direction.DOWN);
			break;
		case java.awt.event.KeyEvent.VK_D:
			player.move(Direction.RIGHT);
			break;
		default:
			break;
		}

	}	
}
