package com.exevan.cyan.framework.dispatch;

import com.exevan.cyan.framework.event.Event;
import com.exevan.cyan.framework.event.IEventListener;

public interface IDispatcher {
	
	public void register(IEventListener... listener);
	
	public void unregister(IEventListener... listener);

	public void postEvent(Event e);
}
