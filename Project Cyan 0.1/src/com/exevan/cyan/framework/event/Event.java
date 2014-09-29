package com.exevan.cyan.framework.event;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public abstract class Event implements Delayed{

	private int type;
	private long delay;
	
	public Event(int type) {
		this.type = type;
		this.delay = 0;
	}

	public int getType() {
		return type;
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
