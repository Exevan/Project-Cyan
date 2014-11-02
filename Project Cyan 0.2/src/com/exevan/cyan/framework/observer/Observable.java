package com.exevan.cyan.framework.observer;

import com.exevan.cyan.framework.event.Event;

public interface Observable {
	
	public void register(Observer observer);
	
	public void unregistrer(Observer observer);
	
	public void dispatchEvent(Event e);

}
