import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	final static int m = 7, n = 8;
	static int[][] map;
	static boolean[][] isUsed;
	static boolean[][] visited;
	final static int[] dx = {0, 1};
	final static int[] dy = {1, 0};
	static int res;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			map = new int[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			visited = new boolean[m][n];
			isUsed = new boolean[7][7];
			res = 0;
			backTrack(0, 0);
			System.out.println("#" + tc + " " + res);
		}
	}
	
	private static void backTrack(int row, int col) {
		if (row == m) {
			res++;
			return;
		}
		if (!visited[row][col]) {
			int one = map[row][col];
			for (int i = 0; i < 2; i++) {
				int xx = row + dx[i];
				int yy = col + dy[i];
				if (checkOut(xx, yy) && !visited[xx][yy]) {
					int two = map[xx][yy];
					if (!isUsed[one][two]) {
						isUsed[one][two] = true;
						isUsed[two][one] = true;
						visited[row][col] = true;
						visited[xx][yy] = true;
						if (col < n - 1) backTrack(row, col+1);
						else backTrack(row+1, 0);
						isUsed[one][two] = false;
						isUsed[two][one] = false;
						visited[row][col] = false;
						visited[xx][yy] = false;
					}
				}
			}
		} else {
			if (col < n - 1) backTrack(row, col+1);
			else backTrack(row+1, 0);
		}
	}
	
	private static boolean checkOut(int x, int y) {
		return x >= 0 && x < m && y >= 0 && y < n;
	}
}
