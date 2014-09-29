package com.exevan.cyan.framework.event;

public class KeyEvent extends Event {
	
	private int key;

	public KeyEvent(int key) {
		super(Event.KEY);
		this.key = key;
	}
	
	public int getKey() {
		return key;
	}

}
