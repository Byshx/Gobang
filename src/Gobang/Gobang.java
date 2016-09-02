package Gobang;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gobang extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel chessBoard = null;

	public static void main(String[] args) {
		new Gobang();
	}

	public Gobang() {
		// TODO Auto-generated constructor stub
		setTitle("五子棋 Created by Byshx");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); // 获取屏幕大小
		int width = 1000;
		int height = 1050;
		boolean visit[][] = new boolean[15][15];
		setSize(width + 10, height);
		setLocation(dimension.width / 2 - this.getWidth() / 2, dimension.height / 2 - this.getHeight() / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		chessBoard = new BordGrid(width);
		chessBoard.setSize((int) (width * 0.90), (int) (width * 0.90));
		MouseHit mouseHit = new MouseHit(width / 16, this, visit, 0);
		chessBoard.addMouseListener(mouseHit);
		this.add(chessBoard,BorderLayout.CENTER);
		chessBoard.add(new Chess_BLACK(62, 62));
		this.add(new Chess_BLACK(62, 62));
		this.add(new Chess_BLACK(62, 248));
		setVisible(true);
	}	 

	class BordGrid extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int Edge_Length = 0;

		public BordGrid(int Edge_Length) {
			this.Edge_Length = Edge_Length;
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
		}
	}

}
