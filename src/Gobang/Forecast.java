package Gobang;

import java.util.HashMap;

public class Forecast {
	private static int[][] map = null;
	private HashMap<String, Location> hashMap = new HashMap<String, Location>();

	public Forecast(int[][] map) {
		// TODO Auto-generated constructor stub
		this.map = map;
		DFS();
	}
	
	public static void DFS(){
		
	}

	public int getWinY(String key) {
		return hashMap.get(key).getY();
	}

	public int getWinX(String key) {
		return hashMap.get(key).getX();
	}

	class Location {
		boolean winNode = false;
		private int y;
		private int x;
		Location parent = null;

		public Location(int y, int x, Location parent) {
			// TODO Auto-generated constructor stub
			this.y = y;
			this.x = x;
			this.parent = parent;
		}

		public int getY() {
			return y;
		}

		public int getX() {
			return x;
		}
	}

}
