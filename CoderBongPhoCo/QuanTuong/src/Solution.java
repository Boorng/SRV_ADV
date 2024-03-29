import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int n, m, p, q, s, t, res;
	final static int[] dx = {-1, 1, 1, -1};
	final static int[] dy = {1, 1, -1, -1};
	static int[] Qx;
	static int[] Qy;
	static int[][] map, direct, visited;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			m = sc.nextInt();
			p = n - sc.nextInt();
			q = sc.nextInt() - 1;
			s = n - sc.nextInt();
			t = sc.nextInt() - 1;
			res = Integer.MAX_VALUE;
			map = new int[n][n];
			direct = new int[n][n];
			visited = new int[n][n];
			Qx = new int[10000];
			Qy = new int[10000];
			
			for (int i = 0; i < m; i++) {
				int a = n - sc.nextInt();
				int b = sc.nextInt() - 1;
				map[a][b] = 1;
			}
			BFS(p, q);
			System.out.println(visited[s][t]);
		}
	}
	
	private static void BFS(int row, int col) {
		int front = 0;
		int rear = 0;
		Qx[rear] = row;
		Qy[rear++] = col;
		visited[row][col] = 1;
		direct[row][col] = -1;
		while (front != rear) {
			int tempX = Qx[front];
			int tempY = Qy[front++];
			for (int i = 0; i < 4; i++) {
				int xx = tempX + dx[i];
				int yy = tempY + dy[i];
				if (checkOut(xx, yy) && direct[xx][yy] != -1 && map[xx][yy] != 1) {
					if (direct[tempX][tempY] == i || direct[tempX][tempY] == -1) {
						if (visited[xx][yy] == 0 || visited[xx][yy] > visited[tempX][tempY]) {
							direct[xx][yy] = i;
							visited[xx][yy] = visited[tempX][tempY];
							Qx[rear] = xx;
							Qy[rear++] = yy;
						}
					} else {
						if (visited[xx][yy] == 0 || visited[xx][yy] > visited[tempX][tempY] + 1) {
							direct[xx][yy] = i;
							visited[xx][yy] = visited[tempX][tempY] + 1;
							Qx[rear] = xx;
							Qy[rear++] = yy;
						}
					}
				}
			}
		}
	}
	
	private static boolean checkOut(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
}
