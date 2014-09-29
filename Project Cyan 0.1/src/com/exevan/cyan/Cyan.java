package com.exevan.cyan;

import com.exevan.cyan.domain.World;
import com.exevan.cyan.framework.dispatch.GameDispatcher;
import com.exevan.cyan.framework.dispatch.InputDispatcher;
import com.exevan.cyan.framework.event.Event;
import com.exevan.cyan.framework.event.GameEventQueue;
import com.exevan.cyan.framework.event.IEventListener;
import com.exevan.cyan.framework.event.KeyEvent;
import com.exevan.cyan.framework.event.TickEvent;
import com.exevan.cyan.ui.CyanUI;

public class Cyan extends Thread implements IEventListener {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Cyan();
	}
	
	private CyanUI ui;
	private World world;
	
	private GameDispatcher gameDispatcher;
	private InputDispatcher inputDispatcher;
	
	
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
		gameDispatcher = new GameDispatcher();
		gameDispatcher.register(ui, world);
		
		inputDispatcher = new InputDispatcher();
		inputDispatcher.register(this);
		ui.setInputDispatcher(inputDispatcher);
	}

	@Override
	public void run() {
		super.run();
		inputDispatcher.start();
		Event tick = new TickEvent();
		while(true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
			gameDispatcher.postEvent(tick);
			gameDispatcher.dispatch();
		}
	}
	
	@Override
	public void handle(Event e) {
		if (e.getType() == Event.KEY)
			this.handleKeyEvent((KeyEvent) e);	
	}
	
	private void handleKeyEvent(KeyEvent e) {
		if(e.getKey() == java.awt.event.KeyEvent.VK_ESCAPE)
			System.exit(NORM_PRIORITY);
	}
	
	
}
