import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int m, n, res, xMin, yMin;
	static int[][] map;
	static boolean[][] visited, visitWater;
	final static int[] dx = {-1, 0, 1, 0};
	final static int[] dy = {0, 1, 0, -1};
	static int[] Qx, Qy;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int tc = 0;
		while (true) {
			tc++;
			m = sc.nextInt();
			n = sc.nextInt();
			if (m == 0 && n == 0) {
				break;
			}
			System.out.print("Case " + tc);
			map = new int[105][105];
			visited = new boolean[105][105];
			visitWater = new boolean[105][105];
			res = 0;
			Qx = new int[10000];
			Qy = new int[10000];
			
			int maxMap = 0;
			int minRia = 2000;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					if (maxMap < map[i][j]) {
						maxMap = map[i][j];
					}
					if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
						if (minRia > map[i][j]) {
							minRia = map[i][j];
							xMin = i;
							yMin = j;
						}
					}
				}
			}

			int mid = minRia == 0 ? 1 : minRia;
			
			while (mid <= maxMap) {
				visitWater = new boolean[105][105];
				visited = new boolean[105][105];
				for (int i = 0; i < m; i++) {
					for (int j = 0; j < n; j++) {
						if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
							if (mid >= map[i][j] && !visitWater[i][j]) {
								BFS(i, j ,mid);
							}
						}
					}
				}
				Qx = new int[10000];
				Qy = new int[10000];
				int count = countRegion();
				if (count > 1) {
					res = mid;
					break;
				}
				mid++;
			}
			if (res > 0) {
				System.out.println(": Island splits when ocean rises " + res + " feet.");
			} else {
				System.out.println(": Island never splits.");
			}
		}
	}
	
	private static void BFS(int x, int y, int step) {
		int front = 0;
		int rear = 0;
		Qx[rear] = x;
		Qy[rear++] = y;
		visitWater[x][y] = true;
		while (front != rear) {
			int tempX = Qx[front];
			int tempY= Qy[front++];
			for (int i = 0; i < 4; i++) {
				int xx = tempX + dx[i];
				int yy = tempY + dy[i];
				if (checkOut(xx, yy) && !visitWater[xx][yy] && map[xx][yy] <= step) {
					visitWater[xx][yy] = true;
					Qx[rear] = xx;
					Qy[rear++] = yy;
				}
			}
		}
	}
	
	private static int countRegion() {
		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && !visitWater[i][j]) {
					BFS(i, j);
					count++;
				}
			}
		}
		return count;
	}
	
	private static void BFS(int x, int y) {
		int front = 0;
		int rear = 0;
		Qx[rear] = x;
		Qy[rear++] = y;
		visited[x][y] = true;
		while (front != rear) {
			int tempX = Qx[front];
			int tempY= Qy[front++];
			for (int i = 0; i < 4; i++) {
				int xx = tempX + dx[i];
				int yy = tempY + dy[i];
				if (checkOut(xx, yy) && !visitWater[xx][yy] && !visited[xx][yy]) {
					visited[xx][yy] = true;
					Qx[rear] = xx;
					Qy[rear++] = yy;
				}
			}
		}
	}
 	
	private static boolean checkOut(int x, int y) {
		return x >= 0 && x < m && y >= 0 && y < n;
	}
}
