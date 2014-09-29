package com.exevan.cyan.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import com.exevan.cyan.framework.dispatch.InputDispatcher;
import com.exevan.cyan.framework.event.Event;
import com.exevan.cyan.framework.event.IEventListener;
import com.exevan.cyan.framework.event.KeyEvent;

public class CyanUI extends JFrame  implements IEventListener {

	private static final long serialVersionUID = 1L;

	private InputDispatcher dispatcher;
	
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
		
		imap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "esc");
		amap.put("esc", new KeyAction(java.awt.event.KeyEvent.VK_ESCAPE));
		
		imap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SPACE, 0), "space");
		amap.put("space", new KeyAction(java.awt.event.KeyEvent.VK_SPACE));
		
	}
	
	public void setInputDispatcher(InputDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	public void close() {
		this.dispose();
		System.exit(0);
	}
	
	@Override
	public void handle(Event e) {
		if (e.getType() == Event.TICK)
			this.repaint();
		if (e.getType() == Event.KEY)
			this.handleKeyEvent((KeyEvent) e);	
	}
	
	private void handleKeyEvent(KeyEvent e) {
		
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
			dispatcher.postEvent(new KeyEvent(key));
		}
	}
}
