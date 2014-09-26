package com.exevan.cyan.event;

import java.awt.AWTEvent;
import java.awt.EventQueue;

public class GameEventQueue extends EventQueue {

	
	@Override
	protected void dispatchEvent(AWTEvent event) {
		System.out.println(event.toString());
		super.dispatchEvent(event);
	}
}