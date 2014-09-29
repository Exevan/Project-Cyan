package com.exevan.cyan.framework.event;

public class KeyEvent extends Event {
	
	private int key;
	public static final int TYPE = 1;

	public KeyEvent(int key) {
		super(KeyEvent.TYPE);
		this.key = key;
	}
	
	public int getKey() {
		return key;
	}

}
