package Gobang;

import java.util.ArrayList;
import java.util.Collections;

public class Algorithm {
	// 五子棋算法
	private static int[][] map;
	private ArrayList<Decision> decision = new ArrayList<Decision>();
	// 对手
	private static int player = -1;
	private static int opponent = -1;

	// 棋盘初始化
	public Algorithm(int opponent, int player, int[][] visit) {
		// TODO Auto-generated constructor stub
		Algorithm.player = player;
		Algorithm.opponent = opponent;
		Algorithm.map = visit;
	}

	public void Analyse(int newY, int newX) {
		decision.clear();
		map[newY][newX] = player;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (map[i][j] != -1)
					continue;
				int JudgeRank = Exploration(i, j);
				if (JudgeRank != -1) {
					Decision d = new Decision(i, j, JudgeRank);
					decision.add(d);
				}
			}
		}
		Collections.sort(decision);
	}

	public int getBestY() {
		return decision.get(0).y;
	}

	public int getBestX() {
		return decision.get(0).x;
	}

	public static int Exploration(int y, int x) {
		int maxRank = -1;
		int temp = 0;
		int countSpecial$1 = 0;
		int countSpecial$2 = 0;
		int[][] add = { { -1, 1 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		for (int i = 0; i < 4; i++) {
			temp = ExploreOpponent(y, x, add[i][0], add[i][1]);
			if (temp == 5)
				countSpecial$1++;
			if (temp == 4)
				countSpecial$2++;
			if (temp > maxRank)
				maxRank = temp;
		}
		if (countSpecial$2 > 0 && countSpecial$1 + countSpecial$2 >= 2)
			return 8; // 等级上升到8
		countSpecial$1 = 0;
		countSpecial$2 = 0;
		for (int i = 0; i < 4; i++) {
			temp = ExplorePlayer(y, x, add[i][0], add[i][1]);
			if (temp == 3)
				countSpecial$1++;
			if (temp == 2)
				countSpecial$2++;
			if (temp > maxRank)
				maxRank = temp;
		}
		if (countSpecial$2 > 0 && countSpecial$1 + countSpecial$2 >= 2)
			return 6; // 等级上升到6
		return maxRank;
	}

	public static int ExploreOpponent(int locy, int locx, int addy, int addx) {
		int y = locy;
		int x = locx;
		int maxRank = -1;
		int opponentChessCount = 0;
		int emptyCount$1 = 0;
		int emptyCount$2 = 0;
		boolean stop = false;
		int count = 0; // 最多遍历到5个位置外；
		locx += addx;
		locy += addy;
		while (locx >= 0 && locx < 15 && locy >= 0 && locy < 15) {
			if (count++ > 5)
				break;
			if (map[locy][locx] == opponent && !stop)
				opponentChessCount++;
			else if (map[locy][locx] == -1) {
				emptyCount$1++;
				stop = true;
			} else
				break;
			locx += addx;
			locy += addy;
		}
		stop = false;
		locy = y;
		locx = x;
		count = 0; // 最多遍历到5个位置外；
		locx -= addx;
		locy -= addy;
		while (locx >= 0 && locx < 15 && locy >= 0 && locy < 15) {
			if (count++ > 5)
				break;
			if (map[locy][locx] == opponent && !stop)
				opponentChessCount++;
			else if (map[locy][locx] == -1) {
				emptyCount$2++;
				stop = true;
			} else
				break;
			locx -= addx;
			locy -= addy;
		}
		if (opponentChessCount >= 4)
			maxRank = 10;// 最高等级
		else if (opponentChessCount >= 3 && emptyCount$1 >= 1 && emptyCount$2 >= 1) {
			if (maxRank < 8)
				maxRank = 8;
		} else if (opponentChessCount >= 3 && (emptyCount$1 >= 1 || emptyCount$2 >= 1)) {
			if (maxRank < 7)
				maxRank = 7;
		} else if (opponentChessCount >= 2
				&& ((emptyCount$1 > 0 && emptyCount$2 > 1) || (emptyCount$1 > 1 && emptyCount$2 > 0))) {
			if (maxRank < 5)
				maxRank = 5;
		} else if (opponentChessCount >= 2 && emptyCount$1 == 1 && emptyCount$2 == 1) {
			if (maxRank < 4)
				maxRank = 4;
		} else if (opponentChessCount >= 1) {
			if (maxRank < 1)
				maxRank = 1;
		}
		return maxRank;
	}

	public static int ExplorePlayer(int locy, int locx, int addy, int addx) {
		int y = locy;
		int x = locx;
		int maxRank = -1;
		int playerChessCount = 0;
		int emptyCount$1 = 0;
		int count = 0; // 最多遍历到5个位置外；
		int emptyCount$2 = 0; // 记录空位的临时变量
		boolean stop = false;
		locx += addx;
		locy += addy;
		while (locx >= 0 && locx < 15 && locy >= 0 && locy < 15) {
			if (count++ > 5)
				break;
			if (map[locy][locx] == player && !stop)
				playerChessCount++;
			else if (map[locy][locx] == -1) {
				emptyCount$1++;
				stop = true;
			} else
				break;
			locx += addx;
			locy += addy;
		}
		locy = y;
		locx = x;
		stop = false;
		count = 0; // 最多遍历到5个位置外；
		locx -= addx;
		locy -= addy;
		while (locx >= 0 && locx < 15 && locy >= 0 && locy < 15) {
			if (count++ > 5)
				break;
			if (map[locy][locx] == player && !stop)
				playerChessCount++;
			else if (map[locy][locx] == -1) {
				emptyCount$2++;
				stop = true;
			} else
				break;
			locx -= addx;
			locy -= addy;
		}
		if (playerChessCount >= 4)
			maxRank = 9;
		else if (playerChessCount >= 3 && emptyCount$1 >= 1 && emptyCount$2 >= 1) {
			if (maxRank < 6)
				maxRank = 6;
		} else if (playerChessCount >= 2
				&& ((emptyCount$1 > 0 && emptyCount$2 > 1) || (emptyCount$1 > 1 && emptyCount$2 > 0))) {
			if (maxRank < 3)
				maxRank = 3;
		} else if (playerChessCount >= 2 && emptyCount$1 == 1 && emptyCount$2 == 1) {
			if (maxRank < 2)
				maxRank = 2;
		} else if (playerChessCount >= 1) {
			if (maxRank < 0)
				maxRank = 0;
		}
		return maxRank;
	}

	class Decision implements Comparable<Decision> {
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
		}

		public int getY() {
			return y;
		}

		public int getX() {
			return x;
		}

		@Override
		public int compareTo(Decision o) {
			// TODO Auto-generated method stub
			if (this.rank > o.rank)
				return -1;
			if (this.rank < o.rank)
				return 1;
			return 0;
		};
	}
}
