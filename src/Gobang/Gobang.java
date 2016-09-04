package Gobang;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class Gobang extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BordGrid chessBoard = null;
	private Stack<Integer> stackY = new Stack<Integer>();
	private Stack<Integer> stackX = new Stack<Integer>();
	private JPopupMenu choose = new JPopupMenu();
	private boolean OpenPVE = true;

	public static void main(String[] args) {
		new Gobang();
	}

	public Gobang() {
		// TODO Auto-generated constructor stub
		setTitle("五子棋 Created by Byshx");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); // 获取屏幕大小
		int width = 1000;
		int height = 1050;
		int visit[][] = new int[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				visit[i][j] = -1;
			}
		}

		setSize(width + 10, height);
		setLocation(dimension.width / 2 - this.getWidth() / 2, dimension.height / 2 - this.getHeight() / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessBoard = new BordGrid(width, visit);
		chessBoard.setSize((int) (width * 0.90), (int) (width * 0.90));
		setBackground(new Color(209, 167, 78));
		this.add(chessBoard);
		setVisible(true);
		MouseHit mouseHit = new MouseHit(width / 16, visit, chessBoard, this, 1, 0, OpenPVE, stackY, stackX, choose);
		chessBoard.addMouseListener(mouseHit);
		JMenuItem item1 = new JMenuItem("PVP");
		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (OpenPVE != false) {
					OpenPVE = false;
					for (int i = 0; i < 15; i++) {
						for (int j = 0; j < 15; j++) {
							visit[i][j] = -1;
						}
					}
					chessBoard.repaint();
					repaint();
					mouseHit.changeModel(OpenPVE);
				}
			}
		});
		JMenuItem item2 = new JMenuItem("PVE");
		item2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (OpenPVE != true) {
					OpenPVE = true;
					for (int i = 0; i < 15; i++) {
						for (int j = 0; j < 15; j++) {
							visit[i][j] = -1;
						}
					}
					chessBoard.repaint();
					repaint();
					mouseHit.changeModel(OpenPVE);
				}
			}
		});
		choose.add(item1);
		choose.add(item2);
	}

	class BordGrid extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int Edge_Length = 0;
		private int[][] visit = new int[15][15];

		public BordGrid(int Edge_Length, int[][] visit) {
			this.Edge_Length = Edge_Length;
			this.visit = visit;
		}

		public BordGrid() {
		}

		public void paint(Graphics g) {
			int UnitLength = Edge_Length / 16;
			int Temp = UnitLength;
			for (int i = 0; i < 15; i++, Temp += UnitLength) {
				g.drawLine(Temp, UnitLength, Temp, UnitLength * 14 + UnitLength);
				g.drawLine(UnitLength, Temp, UnitLength * 14 + UnitLength, Temp);
			}
			g.setColor(Color.BLACK);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(5.0f));
			g2.drawLine(UnitLength * 4, UnitLength * 4, UnitLength * 4, UnitLength * 4);
			g2.drawLine(UnitLength * 8, UnitLength * 4, UnitLength * 8, UnitLength * 4);
			g2.drawLine(UnitLength * 12, UnitLength * 4, UnitLength * 12, UnitLength * 4);
			g2.drawLine(UnitLength * 4, UnitLength * 8, UnitLength * 4, UnitLength * 8);
			g2.drawLine(UnitLength * 8, UnitLength * 8, UnitLength * 8, UnitLength * 8);
			g2.drawLine(UnitLength * 12, UnitLength * 8, UnitLength * 12, UnitLength * 8);
			g2.drawLine(UnitLength * 4, UnitLength * 12, UnitLength * 4, UnitLength * 12);
			g2.drawLine(UnitLength * 8, UnitLength * 12, UnitLength * 8, UnitLength * 12);
			g2.drawLine(UnitLength * 12, UnitLength * 12, UnitLength * 12, UnitLength * 12);

			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (visit[i][j] == 0) {
						g.setColor(Color.WHITE);
						g.fillOval(UnitLength * (j + 1) - 31, UnitLength * (i + 1) - 31, 60, 60);
					}
					if (visit[i][j] == 1) {
						g.setColor(Color.BLACK);
						g.fillOval(UnitLength * (j + 1) - 31, UnitLength * (i + 1) - 31, 60, 60);
					}
				}
			}

		}

	}

}
