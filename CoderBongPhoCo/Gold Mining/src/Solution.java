import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	private static int n, numGold, res;
	private static int[] dxGold, dyGold;
	private static int[][] map, visited;
	private final static int[] dx = {-1, 0, 1, 0};
	private final static int[] dy = {0, 1, 0, -1};
	private static int[] Qx, Qy;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			System.out.println("Case #" + tc);
			n = sc.nextInt();
			numGold = sc.nextInt();
			dxGold = new int[numGold];
			dyGold = new int[numGold];
			for (int i = 0; i < numGold; i++) {
				dxGold[i] = sc.nextInt() - 1;
				dyGold[i] = sc.nextInt() - 1;
			}
			map = new int[n][n];
			visited = new int[n][n];
			res = Integer.MAX_VALUE;
			Qx = new int[10000];
			Qy = new int[10000];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == 1 && !isContain(i, j)) {
						BFS(i, j);
						int[] temp = makeArrForGold();
						int maxTemp = maxArr(temp);
						if (res > maxTemp && maxTemp > 0) {
							res = maxTemp;
						}
					}
				}
			}
			System.out.println(res);
		}
	}
	
	private static boolean isContain(int i, int j) {
		for (int k = 0; k < numGold; k++) {
			if (i == dxGold[k] && j == dyGold[k]) {
				return true;
			}
		}
		return false;
	}
	
	private static int[] makeArrForGold() {
		int[] res = new int[numGold];
		for (int i = 0; i < numGold; i++) {
			res[i] = visited[dxGold[i]][dyGold[i]] - 1;
		}
		return res;
	}
	
	private static int maxArr(int[] arr) {
		int res = -1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > res) {
				res = arr[i];
			}
		}
		return res;
	}
	
	private static void BFS(int xStart, int yStart) {
		visited = new int[n][n];
		int front = 0;
		int rear = 0;
		Qx[rear] = xStart;
		Qy[rear++] = yStart;
		visited[xStart][yStart] = 1;
		while (front != rear) {
			int tempX = Qx[front];
			int tempY = Qy[front++];
			for (int i = 0; i < 4; i++) {
				int xx = tempX + dx[i];
				int yy = tempY + dy[i];
				if (xx >= 0 && xx < n && yy >= 0 && yy < n && map[xx][yy] != 0 && visited[xx][yy] == 0) {
					visited[xx][yy] = visited[tempX][tempY] + 1;
					Qx[rear] = xx;
					Qy[rear++] = yy;
				}
			}
		}
	}
}
