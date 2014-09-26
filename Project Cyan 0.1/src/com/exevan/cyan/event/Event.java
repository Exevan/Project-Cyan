package com.exevan.cyan.event;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Event implements Delayed{

	public static final int TICK = 0;
	
	private int id;
	private long delay;
	
	public Event(int id) {
		this.id = id;
		this.delay = 0;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public long getDelay(TimeUnit unit) {
		return delay;
	}	

	@Override
	public int compareTo(Delayed other) {
		if (this.getDelay(TimeUnit.NANOSECONDS) < this.getDelay(TimeUnit.NANOSECONDS))
			return -1;
		else if (this.getDelay(TimeUnit.NANOSECONDS) > this.getDelay(TimeUnit.NANOSECONDS))
			return 1;
		else
			return 0;
	}
}
