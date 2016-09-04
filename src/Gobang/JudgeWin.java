package Gobang;

public class JudgeWin {

	private int y, x;
	private int[][] map;
	private int P;

	public void setConfig(int y, int x, int[][] map, int P) {
		this.y = y;
		this.x = x;
		this.map = map;
		this.P = P;
	}

	public boolean Win() {
		return horizontal() || vertical() || LeanLine$1() || LeanLine$2();
	}

	public boolean horizontal() {
		int locx = x;
		int count = 0;
		while (locx >= 0 && locx < 15) {
			if (map[y][locx++] == P)
				count++;
			else {
				break;
			}
		}
		locx = x;
		while (locx >= 0 && locx < 15) {
			if (map[y][locx--] == P)
				count++;
			else {
				break;
			}
		}
		count--; // 多算了自己一次
		if (count >= 5)
			return true;
		return false;
	}

	public boolean vertical() {
		int locy = y;
		int count = 0;
		while (locy >= 0 && locy < 15) {
			if (map[locy++][x] == P)
				count++;
			else
				break;
		}
		locy = y;
		while (locy >= 0 && locy < 15) {
			if (map[locy--][x] == P)
				count++;
			else
				break;
		}
		count--;
		if (count >= 5)
			return true;
		return false;
	}

	public boolean LeanLine$1() {
		int locy = y;
		int locx = x;
		int count = 0;
		while (locy >= 0 && locy < 15 && locx>=0 && locx<15) {
			if (map[locy++][locx++] == P)
				count++;
			else
				break;
		}
		locy = y;
		locx = x;
		while (locy >= 0 && locy < 15 && locx>=0 && locx<15) {
			if (map[locy--][locx--] == P)
				count++;
			else
				break;
		}
		count--;
		if (count >= 5)
			return true;
		return false;
	}

	public boolean LeanLine$2() {
		int locy = y;
		int locx = x;
		int count = 0;
		while (locy >= 0 && locy < 15 && locx>=0 && locx<15) {
			if (map[locy++][locx--] == P)
				count++;
			else
				break;
		}
		locy = y;
		locx = x;
		while (locy >= 0 && locy < 15 && locx>=0 && locx<15) {
			if (map[locy--][locx++] == P)
				count++;
			else
				break;
		}
		count--;
		if (count == 5)
			return true;
		return false;
	}
}
