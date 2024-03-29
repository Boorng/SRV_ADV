import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int n, res;
	static int[][] map, visited, visitedZro;
	static int[] Qx, Qy, count;
	final static int[] dx = {-1, 0, 1, 0};
	final static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			System.out.println("Case #" + tc);
			n = sc.nextInt();
			map = new int[n][n];
			res = 0;
			Qx = new int[10000];
			Qy = new int[10000];
			visited = new int[n][n];
			visitedZro = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j ] == 0 && visitedZro[i][j] == 0) {
						setZero(i, j);
						visited = new int[n][n];
						Qx = new int[10000];
						Qy = new int[10000];
					}
				}
			}

			Qx = new int[10000];
			Qy = new int[10000];
			visited = new int[n][n];
			int color = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (visited[i][j] == 0) {
						countAreas(i, j, color);
						color++;
					}
				}
			}
			res = color;
			System.out.println(res);
		}
	}
	
	private static void countAreas(int x, int y, int color) {
		int front = 0;
		int rear = 0;
		Qx[rear] = x;
		Qy[rear++] = y;
		visited[x][y] = 1;
		while (front != rear) {
			int tempX = Qx[front];
			int tempY = Qy[front++];
			for (int i = 0; i < 4; i++) {
				int xx = tempX + dx[i];
				int yy = tempY + dy[i];
				if (checkOut(xx, yy) && visited[xx][yy] == 0 && map[xx][yy] == map[tempX][tempY]) {
					visited[xx][yy] = 1;
					Qx[rear] = xx;
					Qy[rear++] = yy;
				}
			}
		}
	}
	
	private static void setZero(int x, int y) {
		int front = 0;
		int rear = 0;
		int[] saveX = new int[10000];
		int[] saveY = new int[10000];
		int pos = 0;
		saveX[pos] = x;
		saveY[pos++] = y;
		count = new int[6];
		Qx[rear] = x;
		Qy[rear++] = y;
		visited[x][y] = 1;
		visitedZro[x][y] = 1;
		while (front != rear) {
			int tempX = Qx[front];
			int tempY = Qy[front++];
			for (int i = 0; i < 4; i++) {
				int xx = tempX + dx[i];
				int yy = tempY + dy[i];
				if (checkOut(xx, yy) && visited[xx][yy] == 0) {
					if (map[tempX][tempY] == 0) {
						visited[xx][yy] = 1;
						Qx[rear] = xx;
						Qy[rear++] = yy;
						if (map[xx][yy] == 0) {
							visitedZro[xx][yy] = 1;
							saveX[pos] = xx;
							saveY[pos++] = yy;
						} else {
							if (visitedZro[xx][yy] == 0) {
								count[map[xx][yy]]++;
							}	
						}
					} else {
						if (map[xx][yy] == map[tempX][tempY] && visitedZro[xx][yy] == 0) {
							visited[xx][yy] = 1;
							Qx[rear] = xx;
							Qy[rear++] = yy;
							count[map[xx][yy]]++;
						}
					}
				}
			}
		}
		int maxCount = 0;
		int max = 0;
		for (int i = 5; i >= 0; i--) {
			if (count[i] > maxCount && i != 0) {
				maxCount = count[i];
				max = i;
			}
		}
		for (int i = 0; i < pos; i++) {
			map[saveX[i]][saveY[i]] = max;
		}
	}
	
	private static boolean checkOut(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
}
