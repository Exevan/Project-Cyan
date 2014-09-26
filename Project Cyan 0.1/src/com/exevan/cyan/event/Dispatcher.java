package com.exevan.cyan.event;

import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.DelayQueue;

public class Dispatcher {
	
	private Queue<Event> eventQueue;
	
	private Vector<EventListener> inputListeners;
	private Vector<EventListener> worldListeners;
	
	public Dispatcher() {
		this.eventQueue = new DelayQueue<Event>();
		this.inputListeners = new Vector<EventListener>();
		this.worldListeners = new Vector<EventListener>();
	}
	
	public void addInputListener(EventListener... listeners) {
		for (EventListener listener : listeners) {
			inputListeners.add(listener);
		} 
	}
	
	public void addWorldListener(EventListener... listeners) {
		for (EventListener listener : listeners) {
			worldListeners.add(listener);
		} 
	}
	
	public void publish(Event e) {
		eventQueue.offer(e);
	}
	
	public void dispatch() {
		Event e = eventQueue.poll();
		if (e.getId() == Event.TICK) {
			System.out.println("Dispatching tick event");
			dispatchTick(e);
		}
	}
	
	private void dispatchTick(Event e) {
		for (EventListener listener : inputListeners)
			listener.handle(e);
	}
	
	private void dispatchInputEvent(Event e) {
		for (EventListener listener : inputListeners)
			listener.handle(e);
	}
	
	private void dispatchWorldEvent(Event e) {
		for (EventListener listener : worldListeners)
			listener.handle(e);
	}

}
