import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	private static int m, n, res, countDirty;
	private static int[][] map, visited, weight, dust;  
	private static int[] xDirtys, yDirtys, Qx, Qy, visitedDirty;
	
	private final static int[] dx = {-1, 0, 1, 0};
	private final static int[] dy = {0, 1, 0, -1};
 	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("D:\\AlgorithmSRV\\RobotClean\\src\\input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			m = sc.nextInt();
			n = sc.nextInt();
			map = new int[m][n];
			visited = new int[100][100];
			dust = new int[m][n];
			countDirty = 1;
			res = Integer.MAX_VALUE;
			xDirtys = new int[100];
			yDirtys = new int[100];
			visitedDirty = new int[100];
			weight = new int[100][100];
			Qx = new int[10000];
			Qy = new int[10000];
			
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 3) {
						xDirtys[0] = i;
						yDirtys[0] = j;
						dust[i][j] = 0;
					}
					if (map[i][j] == 1) {
						xDirtys[countDirty] = i;
						yDirtys[countDirty] = j;
						dust[i][j] = countDirty;
						countDirty++;
					}
				}
			}
			
			for (int i = 0; i < countDirty; i++) {
				BFS(xDirtys[i], yDirtys[i]);
				visited = new int[100][100];
				Qx = new int[10000];
				Qy = new int[10000];
			}
			backTrack(0, 1, 0);
			
			System.out.println(check() ? -1 : res);
		}
	}
	
	private static boolean check() {
		for (int i = 0; i < countDirty; i++) {
			int temp = 0;
			for (int j = 0; j < countDirty; j++) {
				temp += weight[i][j];
			}
			if (temp == 0) return true;
		}
		return false;
	}
	
	private static void backTrack(int index, int count, int compare) {
		if (count == countDirty) {
			if (res > compare) {
				res = compare;
			}
			return;
		}
		if (res < compare) return;
		for (int i = 1; i < countDirty; i++) {
			if (visitedDirty[i] == 0) {
				visitedDirty[i] = 1;
				backTrack(i, count + 1, compare + weight[index][i]);
				visitedDirty[i] = 0;
			}
		}
	}
	
	private static void BFS(int xStart, int yStart) {
		int front = 0;
		int rear = 0;
		visited[xStart][yStart] = 1;
		Qx[rear] = xStart;
		Qy[rear++] = yStart;
		while (front != rear) {
			int tempX = Qx[front];
			int tempY = Qy[front++];
			for (int i = 0; i < 4; i++) {
				int xx = tempX + dx[i];
				int yy = tempY + dy[i];
				if (checkOut(xx, yy) && map[xx][yy] != 2 && visited[xx][yy] == 0) {
					visited[xx][yy] = visited[tempX][tempY] + 1;
					Qx[rear] = xx;
					Qy[rear++] = yy;
					if (map[xx][yy] == 1) {
						int start = dust[xStart][yStart];
						int end = dust[xx][yy];
						weight[start][end] = visited[xx][yy] - 1;
						weight[end][start] = visited[xx][yy] - 1;
					}
				}
			}
		}
	}
	
	private static boolean checkOut(int x, int y) {
		return x >= 0 && x < m && y >= 0 && y < n;
	}
}

