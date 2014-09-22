package com.exevan.cyan.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Dispatcher {
	
	private Vector<ActionListener> listeners;
	
	public Dispatcher(ActionListener... listeners) {
		this.listeners = new Vector<ActionListener>();
		for (ActionListener listener : listeners)
			this.listeners.add(listener);
	}
	
	public void dispatch(ActionEvent e) {
		for (ActionListener listener : listeners)
			listener.actionPerformed(e);
	}

}
