package com.exevan.cyan;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import com.exevan.cyan.domain.World;
import com.exevan.cyan.event.Event;
import com.exevan.cyan.ui.CyanUI;


public class Cyan extends Thread {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Cyan();
	}
	
	private Vector<ActionListener> listeners;
	
	private CyanUI ui;
	private World world;
	
	public Cyan() {
		initWorld();
		initUI();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initListeners();
		this.start();
	}
	
	
	private void initListeners() {
		this.listeners = new Vector<ActionListener>();
		listeners.add(ui);
		listeners.add(world);
	}
	
	private void initWorld() {
		world = new World();
	}
	
	private void initUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ui = new CyanUI();
					ui.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void run() {
		super.run();
		while(true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
			tick();
		}
	}
	
	private void tick() {
		synchronized (this) {
			ActionEvent e = new ActionEvent(this, Event.TICK, "tick");
			for (ActionListener l : listeners) {
				l.actionPerformed(e);
			}
		}
	}
}
