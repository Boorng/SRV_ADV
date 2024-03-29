import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int n, minTS, maxTS, minMap, maxMap, res;
	static int[][] map, visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[] Qx, Qy;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			map = new int[n][n];
			minMap = Integer.MAX_VALUE;
			maxMap = Integer.MIN_VALUE;
			res = -1;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					if (minMap > map[i][j]) minMap = map[i][j];
					if (maxMap < map[i][j]) maxMap = map[i][j];
				}
			}
			Qx = new int[100000];
			Qy = new int[100000];
			visited = new int[n][n];
			minTS = Math.min(map[0][0], map[n-1][n-1]);
			maxTS = Math.max(map[0][0], map[n-1][n-1]);
			int left = maxTS - minTS;
			int right = maxMap - minMap;
			while (left < right) {
				int mid = (left + right) / 2;
				BFS(mid);
				if (visited[n - 1][n - 1] == 1) {
					res = mid;
					right = mid;
				} else {
					left = mid + 1;
				}
			}
			System.out.println(res);
		}
	}
	
	private static void BFS(int step) {
		visited = new int[n][n];
		int front = 0;
		int rear = 0;
		int a = maxTS - step;
		if (a < 0) a = 0;
		while (a <= minTS) {
			visited[0][0] = 1;
			Qx[rear] = 0;
			Qy[rear++] = 0;
			int b = a + step;
			while (front != rear) {
				int tempX = Qx[front];
				int tempY = Qy[front++];
				for (int i = 0; i < 4; i++) {
					int xx = tempX + dx[i];
					int yy = tempY + dy[i];
					if (checkOut(xx, yy) && visited[xx][yy] == 0 && map[xx][yy] >= a && map[xx][yy] <= b) {
						visited[xx][yy] = 1;
						Qx[rear] = xx;
						Qy[rear++] = yy;
						if (xx == n - 1 && yy == n - 1) return;
					}
				}
			}
			a++;
		}
	}
	
	private static boolean checkOut(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
}
