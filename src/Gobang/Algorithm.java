package Gobang;

import java.util.ArrayList;

public class Algorithm {
	private static int[][] map = new int[225][225];
	private ArrayList<Decision> decision = new ArrayList<Decision>();
	// 玩家
	private static int player = -1;
	// 对手
	private static int opponent = -1;

	public static void main(String[] args) {

	}

	// 棋盘初始化
	public Algorithm(int player, int opponent) {
		// TODO Auto-generated constructor stub
		Algorithm.player = player;
		Algorithm.opponent = opponent;
		for (int i = 0; i < 225; i++) {
			for (int j = 0; j < 225; j++) {
				map[i][j] = -1;
			}
		}
	}

	public void Analyse() {
		for (int i = 0; i < 225; i++) {
			for (int j = 0; j < 225; j++) {
				if (map[i][j] != -1)
					continue;
				int JudgeRank = Exploration(i, j);
				if (JudgeRank != -1) {
					Decision d = new Decision(i, j, JudgeRank);
					decision.add(d);
				}
			}
		}
	}

	public static int Exploration(int y, int x) {
		int maxRank = -1;
		int opponentChessCount = 0;
		int playerChessCount = 0;
		int emptyCount = 0;
		int locy = y + 1;
		int locx = x;
		while (locy >= 0 && locy < 225 && emptyCount<2) {
			if (map[locy][locx] == -1)
				emptyCount++;
			else if(map[locy][locx] == opponent){
				opponentChessCount++;
			}
			else{
				playerChessCount++;
			}
		}
		return maxRank;
	}

	class Decision {
		private int y, x;
		// 决策等级
		private int rank;

		public Decision(int y, int x, int rank) {
			// TODO Auto-generated constructor stub
			this.y = y;
			this.x = x;
			this.rank = rank;
		}

		public Decision() {
		};
	}
}
