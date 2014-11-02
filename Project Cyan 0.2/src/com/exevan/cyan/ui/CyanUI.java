package com.exevan.cyan.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import com.exevan.cyan.domain.world.World;

public class CyanUI extends JFrame {
	
	private World world;

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Display display;
	@SuppressWarnings("unused")
	private ContextBox contextBox;
	@SuppressWarnings("unused")
	private LogBox logBox;

	public CyanUI(World world) {
		this.world = world;
		initSelf();
		initLayout();
		initContainers();
		initKeyBindings();
		this.setVisible(true);
	}

	private void initSelf() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0, 0, screen.width, screen.height);
		this.setUndecorated(true);
	}

	private void initLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[]{0, 0, 0};
		layout.rowHeights = new int[]{0, 0};
		layout.columnWeights = new double[]{1.0, 1.0, 1.0};
		layout.rowWeights = new double[]{1.0, 1.0};
		this.getContentPane().setLayout(layout);
	}

	private void initContainers() {
		this.setDisplay(new Display(world));
		this.setContextBox(new ContextBox());
		this.setLogBox(new LogBox());
	}

	public void setDisplay(Display display) {
		this.display = display;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.getContentPane().add(display, gbc);
	}

	public void setContextBox(ContextBox contextBox) {
		this.contextBox = contextBox;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.getContentPane().add(contextBox, gbc);
	}

	public void setLogBox(LogBox logBox) {
		this.logBox = logBox;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.getContentPane().add(logBox, gbc);
	}
	
	private void initKeyBindings() {
		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
		this.getRootPane().getActionMap().put("ESCAPE", new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);;
			}
		});
	}
	
	public static final int DEFUALT_BOTTOM_HEIGHT = 300;
}