package com.exevan.cyan.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import com.exevan.cyan.event.Dispatcher;
import com.exevan.cyan.event.Event;

public class CyanUI extends JFrame  implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Dispatcher dispatcher;
	
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
		
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
		amap.put("space", new KeyAction(KeyEvent.VK_SPACE));
		
	}
	
	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	public void close() {
		this.dispose();
		System.exit(0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getID() == Event.TICK)
			this.repaint();
	}
	
	private class CloseAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("closing window");
			close();
		}
	}
	
	private class KeyAction extends AbstractAction {
		
		int key;
		
		public KeyAction(int key) {
			this.key = key;
		}
		
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("key " + key + " pressed");
		}
	}
}
