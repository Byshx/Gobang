package Gobang;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MouseHit implements MouseListener {
	private int UnitLength;
	private int locX, locY;
	private boolean[][] visit;
	private JFrame frame;
	private int player;

	public MouseHit(int UnitLength, JFrame frame, boolean[][] visit, int player) {
		// TODO Auto-generated constructor stub
		this.UnitLength = UnitLength;
		this.frame = frame;
		this.visit = visit;
		this.player = player;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int tempx = 0;
		int tempy = 0;
		int x = e.getX();
		int y = e.getY();
		if (UnitLength <= x && x <= 15 * UnitLength && UnitLength <= y && y <= 15 * UnitLength) {
			while (true) {
				if (tempx > x)
					break;
				tempx += UnitLength;
			}
			while (true) {
				if (tempy > y)
					break;
				tempy += UnitLength;
			}
			if (tempx > x + 0.5 * UnitLength) {
				locX = tempx - UnitLength;
			} else
				locX = tempx;
			if (tempy > y + 0.5 * UnitLength) {
				locY = tempy - UnitLength;
			} else
				locY = tempy;

			if (!visit[locX / 62 - 1][locY / 62 - 1]) { // 当前位置没有棋子，可继续执行
				if (player == 0) { // 白棋
					Chess_White chess_White = new Chess_White(locX, locY);
					frame.add(chess_White);	
					frame.repaint();
					System.out.println(frame.getComponentCount());
					player = 1;
					visit[locX / 62 - 1][locY / 62 - 1] = true;
				} else {
					Chess_BLACK chess_BLACK = new Chess_BLACK(locX, locY);
					frame.add(chess_BLACK);
					frame.repaint();
					System.out.println(frame.getComponentCount());
					player = 0;
					visit[locX / 62 - 1][locY / 62 - 1] = true;
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int tempx = 0;
		int tempy = 0;
		int x = e.getX();
		int y = e.getY();
		if (UnitLength <= x && x <= 15 * UnitLength && UnitLength <= y && y <= 15 * UnitLength) {
			while (true) {
				if (tempx > x)
					break;
				tempx += UnitLength;
			}
			while (true) {
				if (tempy > y)
					break;
				tempy += UnitLength;
			}
			if (tempx > x + 0.5 * UnitLength) {
				locX = tempx - UnitLength;
			} else
				locX = tempx;
			if (tempy > y + 0.5 * UnitLength) {
				locY = tempy - UnitLength;
			} else
				locY = tempy;

			if (!visit[locX / 62 - 1][locY / 62 - 1]) { // 当前位置没有棋子，可继续执行
				if (player == 0) { // 白棋
					Chess_White chess_White = new Chess_White(locX, locY);
					frame.add(chess_White);
					frame.repaint();
					player = 1;
				} else {
					Chess_BLACK chess_BLACK = new Chess_BLACK(locX, locY);
					frame.add(chess_BLACK);
					frame.repaint();
					player = 0;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
