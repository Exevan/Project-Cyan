package com.exevan.cyan.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class CyanUI extends JFrame{

	private static final long serialVersionUID = 1L;

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		initializeKeybindings();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0, 0,screen.width, screen.height);
		this.setContentPane(new Display());
		this.setIgnoreRepaint(true);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	private void initializeKeybindings() {
		InputMap imap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = this.getRootPane().getActionMap();
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "esc");
		amap.put("esc", new CloseAction());
	}
	
	public void close() {
		this.dispose();
		System.exit(1000);
	}
	
	private class CloseAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("closing window");
			close();
		}
	}
	
}
