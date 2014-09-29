package com.exevan.cyan.framework.event;

public class TickEvent extends Event {

	public static final int TYPE = 0;

	public TickEvent() {
		super(TickEvent.TYPE);
	}
}
