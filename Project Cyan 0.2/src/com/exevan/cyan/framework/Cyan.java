package com.exevan.cyan.framework;

import java.awt.EventQueue;

import com.exevan.cyan.domain.world.World;
import com.exevan.cyan.ui.CyanUI;

public class Cyan {
	
	@SuppressWarnings("unused")
	private CyanUI ui;
	private World world;

	public static void main(String[] args) {
		new Cyan();
	}
	
	public Cyan() {
		initWorld();
		initUI();
	}
	
	private void initWorld() {
		world = new World(4827928831l, 1024, 1024);
	}
	
	private void initUI() {
		EventQueue.invokeLater(new Runnable() {		
			@Override
			public void run() {
				ui = new CyanUI(world);
			}
		});
	}
	
}
