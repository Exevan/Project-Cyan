package com.exevan.cyan;

import java.awt.EventQueue;

import com.exevan.cyan.ui.CyanUI;


public class Cyan {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CyanUI window = new CyanUI();
					window.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
}
