import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	private final static int n = 5;
	private static int m, res, check;
	private static int[][] map;
	private static ScreenMap[] listScreenMap;
	private static int[] dx = {-1, -1, -1};
	private static int[] dy = {-1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			System.out.println("Case #" + tc);
			m = sc.nextInt();
			map = new int[m][n];
			int[][] mapCop = new int[m][n];
			listScreenMap = new ScreenMap[m];
			res = 0;
			check = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					mapCop[i][j] = map[i][j];
				}
				listScreenMap[i] = new ScreenMap(mapCop);
			}
			for (int i = 0; i < 5; i++) {
				int[][] tempMap = listScreenMap[i].getScreenMap();
				for (int j = 0 ; j <= i; j++) {
					for (int k = 0; k < n; k++) {
						if (tempMap[j][k] == 2) {
							tempMap[j][k] = 0;
						}
					}
				}
				listScreenMap[i].setScreenMap(tempMap);
				backTrack(m, 2, 0, listScreenMap[i].getScreenMap());
			}
			for (int i = 5; i < m; i++) {
				int[][] cop = copMap(map);
				listScreenMap[i].setScreenMap(cop);
				int[][] tempMap = listScreenMap[i].getScreenMap();
				for (int j = i - 4; j <= i; j++) {
					for (int k = 0; k < n; k++) {
						if (tempMap[j][k] == 2) {
							tempMap[j][k] = 0;
						}
					}
				}
				
				listScreenMap[i] = new ScreenMap(tempMap);
				backTrack(m, 2, 0, listScreenMap[i].getScreenMap());
			}
			System.out.println(check == 0 ? -1 : res);
		}
	}
	
	private static int[][] copMap(int[][] map) {
		int[][] res = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				res[i][j] = map[i][j];
			}
		}
		return res;
	}
	
	private static void backTrack(int x, int y, int coin, int[][] map) {
		if (coin < 0) {
			return;
		}
		if (x == 0) {
			if (coin < 0) {
				return;
			}
			check = 1;
			if (res < coin) {
				res = coin;
			}
			return;
		}
		for (int i = 0; i < 3; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if (xx >= 0 && xx < m && yy >= 0 && yy < n) {
				if (map[xx][yy] == 0) backTrack(xx, yy, coin, map);
				if (map[xx][yy] == 1) backTrack(xx, yy, coin + 1, map);
				if (map[xx][yy] == 2) backTrack(xx, yy, coin - 1, map);
			}
		}
	}
}

class ScreenMap {
	private int[][] screenMap;
	
	public ScreenMap(int[][] map) {
		this.setScreenMap(map);
	}

	public int[][] getScreenMap() {
		return screenMap;
	}

	public void setScreenMap(int[][] screenMap) {
		this.screenMap = screenMap;
	}
	
	public void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(screenMap[i][j] + " ");
			}
			System.out.println();
		}
	}
}