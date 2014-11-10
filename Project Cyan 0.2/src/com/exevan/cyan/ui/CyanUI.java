package com.exevan.cyan.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import com.exevan.cyan.domain.util.Direction;
import com.exevan.cyan.domain.world.World;

public class CyanUI extends JFrame {
	
	private World world;

	private static final long serialVersionUID = 1L;
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
		initMouseControls();
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
		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0), "Z");
		this.getRootPane().getActionMap().put("Z", new Pan(Direction.NORTH));

		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), "Q");
		this.getRootPane().getActionMap().put("Q", new Pan(Direction.WEST));

		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "S");
		this.getRootPane().getActionMap().put("S", new Pan(Direction.SOUTH));

		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "D");
		this.getRootPane().getActionMap().put("D", new Pan(Direction.EAST));
		
		this.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
		this.getRootPane().getActionMap().put("ESCAPE", new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);;
			}
		});
	}
	
	private void initMouseControls() {
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				super.mouseWheelMoved(e);
				int rot = e.getWheelRotation();
				int scale = display.getWorldScale();
				display.setWorldScale(scale + rot);
				repaint();
			}
			
		};
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
		this.addMouseWheelListener(mouseAdapter);
	}
	
	public static final int DEFUALT_BOTTOM_HEIGHT = 300;
	
	private class Pan extends AbstractAction {

		private static final long serialVersionUID = 1L;
		private Direction dir;
		
		public Pan(Direction dir) {
			this.dir = dir;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int x = display.getWorldX() + dir.getDx();
			int y = display.getWorldY() + dir.getDy();
			display.setWorldX(x);
			display.setWorldY(y);
			repaint();
		}
		
	}
}