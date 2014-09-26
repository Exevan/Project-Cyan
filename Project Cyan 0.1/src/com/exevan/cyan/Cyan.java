package com.exevan.cyan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import com.exevan.cyan.domain.World;
import com.exevan.cyan.event.Dispatcher;
import com.exevan.cyan.event.Event;
import com.exevan.cyan.event.GameEventQueue;
import com.exevan.cyan.ui.CyanUI;


public class Cyan extends Thread {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Cyan();
	}
	
	private CyanUI ui;
	private World world;
	private Dispatcher dispatcher;
	
	public Cyan() {
		initWorld();
		initUI();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initDispatcher();
		this.setName("Game loop");
		this.start();
	}
	
	private void initWorld() {
		world = new World();
	}
	
	private void initUI() {
		GameEventQueue.invokeLater(new Runnable() {
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
	
	private void initDispatcher() {
		dispatcher = new Dispatcher();
		dispatcher.addInputListener(ui, world);
		//dispatcher.addWorldListener(ui, world);
		ui.setDispatcher(dispatcher);
	}

	@Override
	public void run() {
		super.run();
		while(true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
			dispatcher.publish(new Event(Event.TICK));
			dispatcher.dispatch();
		}
	}
}
