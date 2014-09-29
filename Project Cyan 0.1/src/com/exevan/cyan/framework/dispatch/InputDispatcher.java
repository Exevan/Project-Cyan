package com.exevan.cyan.framework.dispatch;

import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.DelayQueue;

import com.exevan.cyan.framework.event.Event;
import com.exevan.cyan.framework.event.IEventListener;

public class InputDispatcher extends Thread implements IDispatcher {

	private Queue<Event> eventQueue;

	private Vector<IEventListener> listeners;

	public InputDispatcher() {
		this.eventQueue = new DelayQueue<Event>();
		this.listeners = new Vector<IEventListener>();
	}

	@Override
	public void register(IEventListener... listeners) {
		for (IEventListener listener : listeners)
			this.listeners.add(listener); 
	}

	@Override
	public void unregister(IEventListener... listeners) {
		for (IEventListener listener : listeners)
			this.listeners.remove(listener);
	}

	public void postEvent(Event e) {
		eventQueue.offer(e);
	}

	public void dispatch() {
		Event e = eventQueue.poll();
		System.out.println("dispatching: " + e.toString());
		for(IEventListener listener : listeners)
			listener.handle(e);
	}

	@Override
	public void run() {
		super.run();
		while(true)
			if(! eventQueue.isEmpty())
				dispatch();	
	}


}
