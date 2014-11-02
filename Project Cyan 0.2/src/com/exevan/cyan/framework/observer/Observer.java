package com.exevan.cyan.framework.observer;

import com.exevan.cyan.framework.event.Event;

public interface Observer {
	
	public void handle(Event event);

}
