import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	private static int m, n, xStart, yStart, xTarget, yTarget, res;
	private static int[][] map;
	private static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			System.out.println("Case #" + tc);
			m = sc.nextInt();
			n = sc.nextInt();
			map = new int[m][n];
			res = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 2) {
						xStart = i;
						yStart = j;
					}
					if (map[i][j] == 3) {
						xTarget = i;
						yTarget = j;
					}
				}
			}
			
			int left = 0;
			int right = m;
			while (left <= right) {
				int mid = (left + right) / 2;
				visited = new boolean[m][n];
				backTrack(xStart, yStart, mid);
				if (visited[xTarget][yTarget]) {
					right = mid - 1;
					res = mid;
				} else {
					left = mid + 1;
				}
			}
			
			System.out.println(res);
		}
	}
	
	private static void backTrack(int row, int col, int step) {
		visited[row][col] = true;
		if (checkOut(row, col-1) && !visited[row][col-1] && map[row][col-1] != 0) {
			backTrack(row, col-1, step);
		}
		if (checkOut(row, col+1) && !visited[row][col+1] && map[row][col+1] != 0) {
			backTrack(row, col+1, step);
		}
		for (int i = 1; i <= step; i++) {
			if (checkOut(row-i, col) && !visited[row-i][col] && map[row-i][col] != 0) {
				backTrack(row-i, col, step);
			}
			if (checkOut(row+i, col) && !visited[row+i][col] && map[row+i][col] != 0) {
				backTrack(row+i, col, step);
			}
		}
	}
	
	private static boolean checkOut(int x, int y) {
		return x >= 0 && x < m && y >= 0 && y < n;
	}
}
