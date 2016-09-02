package Gobang;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Chess_BLACK extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int locX, locY;

	public Chess_BLACK(int locX, int locY) {
		// TODO Auto-generated constructor stub
		setSize(30, 30);
		this.locX = locX;
		this.locY = locY;
	}

	public void paint(Graphics g) {
		g.fillOval(locX - 31, locY - 31, 60, 60);
		g.setColor(Color.BLACK);
	}
}
