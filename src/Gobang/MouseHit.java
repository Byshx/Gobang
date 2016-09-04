package Gobang;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class MouseHit extends MouseAdapter {
	private int UnitLength;
	private int locX, locY;
	private int[][] visit;
	private int player;
	private JPanel chessBoard;
	private JFrame frame;
	private boolean OpenPVE = false;
	private Algorithm algorithm;
	private JudgeWin judgeWin = new JudgeWin();
	private Stack<Integer> stackY = null;
	private Stack<Integer> stackX = null;
	private JPopupMenu choose = null;

	public MouseHit(int UnitLength, int[][] visit, JPanel chessBoard, JFrame frame, int player, int opponent,
			boolean OpenPVE, Stack<Integer> stackY, Stack<Integer> stackX, JPopupMenu choose) {
		// TODO Auto-generated constructor stub
		this.UnitLength = UnitLength;
		this.visit = visit;
		this.player = player;
		this.OpenPVE = OpenPVE;
		this.chessBoard = chessBoard;
		this.frame = frame;
		this.stackY = stackY;
		this.stackX = stackX;
		this.choose = choose;
		algorithm = new Algorithm(opponent, player, visit);
	}

	public void changeModel(boolean Model) {
		OpenPVE = Model;
		player = 1;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		int tempx = 0;
		int tempy = 0;
		int x = e.getX();
		int y = e.getY();
		if (UnitLength <= x && x <= 15 * UnitLength && UnitLength <= y && y <= 15 * UnitLength) {
			if (e.getButton() == MouseEvent.BUTTON1) {
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
				int Y = locY / 62 - 1;
				int X = locX / 62 - 1;
				boolean win = false;
				if (visit[Y][X] == -1) { // µ±Ç°Î»ÖÃÃ»ÓÐÆå×Ó£¬¿É¼ÌÐøÖ´ÐÐ
					stackY.push(Y);
					stackX.push(X);
					if (player == 0) { // °×Æå
						player = 1;
						visit[Y][X] = 0;
						chessBoard.repaint();
						judgeWin.setConfig(Y, X, visit, 0);
						if (judgeWin.Win()) {
							JOptionPane.showMessageDialog(frame, "°×ÆåÊ¤");
							for (int i = 0; i < 15; i++) {
								for (int j = 0; j < 15; j++) {
									visit[i][j] = -1;
								}
							}
							chessBoard.repaint();
							frame.repaint();
						}
					} else {
						player = 0;
						visit[Y][X] = 1;
						chessBoard.repaint();
						judgeWin.setConfig(Y, X, visit, 1);
						if (judgeWin.Win()) {
							win = true;
							JOptionPane.showMessageDialog(frame, "ºÚÆåÊ¤");
							for (int i = 0; i < 15; i++) {
								for (int j = 0; j < 15; j++) {
									visit[i][j] = -1;
								}
							}
							chessBoard.repaint();
							frame.repaint();
						}
						if (!win) {
							if (OpenPVE) {
								player = 1;
								algorithm.Analyse(Y, X);
								visit[algorithm.getBestY()][algorithm.getBestX()] = 0;
								stackY.push(algorithm.getBestY());
								stackX.push(algorithm.getBestX());
								chessBoard.repaint();
								judgeWin.setConfig(algorithm.getBestY(), algorithm.getBestX(), visit, 0);
								if (judgeWin.Win()) {
									JOptionPane.showMessageDialog(frame, "°×ÆåÊ¤");
									for (int i = 0; i < 15; i++) {
										for (int j = 0; j < 15; j++) {
											visit[i][j] = -1;
										}
									}
									chessBoard.repaint();
									frame.repaint();
								}
							}
						} else {
							player = 1;
							win = false;
						}
					}
				}
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				if (!stackY.isEmpty()) {
					int yy = stackY.pop();
					int xx = stackX.pop();
					int tempPlayer = visit[yy][xx];
					visit[yy][xx] = -1;
					if (OpenPVE) {
						visit[stackY.pop()][stackX.pop()] = -1;
					} else {
						player = tempPlayer;
					}
					chessBoard.repaint();
					frame.repaint();
				}
			}
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			choose.show(frame, e.getX(), e.getY());
		}
	}
}
