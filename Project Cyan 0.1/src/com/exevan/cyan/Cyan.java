package com.exevan.cyan;

import java.awt.EventQueue;

import com.exevan.cyan.domain.World;
import com.exevan.cyan.ui.CyanUI;


public class Cyan {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Cyan();
	}
	
	
	
	private CyanUI ui;
	private World world;
	
	public Cyan() {
		initWorld();
		initUI();
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
}
