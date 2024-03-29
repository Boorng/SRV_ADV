import java.util.Scanner;

public class Solution {
	static int res, n;
	static int[][] map;
	static boolean[][] visited;
	final static int[] dx = {0, 1};
	final static int[] dy = {1, 0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		res = 0;
		n = sc.nextInt();
		map = new int[4][n];
		visited = new boolean[4][n];
		backTrack(0, 0);
		System.out.println(res);
	}
	
	static void backTrack(int row, int col) {
		if (row == 4) {
			res++;
			return;
		}
		if (!visited[row][col]) {
			for (int i = 0; i < 2; i++) {
				int xx = row + dx[i];
				int yy = col + dy[i];
				if (checkOut(xx, yy) && !visited[xx][yy]) {
					visited[row][col] = true;
					visited[xx][yy] = true;
					if (col < n - 1) backTrack(row, col+1);
					else backTrack(row+1, 0);
					visited[row][col] = false;
					visited[xx][yy] = false;
				}
			}
		} else {
			if (col < n - 1) backTrack(row, col+1);
			else backTrack(row+1, 0);
		}
	}
	
	static boolean checkOut(int x, int y) {
		return x >= 0 && x < 4 && y >= 0 && y < n;
	}
}
