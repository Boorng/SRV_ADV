import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	private static int m, n, res, xStart, yStart, limit;
	private static int[][] map, visited;
	private static int[] Qx, Qy;
	private final static int[] dx = {-1, 0, 1, 0};
	private final static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			System.out.println("Case #" + tc);
			m = sc.nextInt();
			n = sc.nextInt();
			xStart = sc.nextInt();
			yStart = sc.nextInt();
			limit = sc.nextInt();
			map = new int[m][n];
			visited = new int[m][n];
			Qx = new int[10000];
			Qy = new int[10000];
			res = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			BFS(xStart, yStart);
			System.out.println(res);
		}
	}
	
	private static void BFS(int row, int col) {
		int front = 0;
		int rear = 0;
		Qx[rear] = row;
		Qy[rear++] = col;
		visited[row][col] = 1;
		res++;
		while (front != rear) {
			int tempX = Qx[front];
			int tempY = Qy[front++];
			for (int i = 0; i < 4; i++) {
				int xx = tempX + dx[i];
				int yy = tempY + dy[i];
				if (xx >= 0 && xx < m && yy >= 0 && yy < n && visited[xx][yy] == 0 && map[xx][yy] != 0) {
					visited[xx][yy] = visited[tempX][tempY] + 1;
					if (visited[xx][yy] >= limit) {
						return;
					}
					Qx[rear] = xx;
					Qy[rear++] = yy;
					res++;
				}
			}
		}
	}
}
