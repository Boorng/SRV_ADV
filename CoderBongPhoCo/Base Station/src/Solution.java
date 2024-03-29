import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	private static int m, n, res, pos, resTemp;
	private static int[][] map, visited;
	private final static int[] dx1 = {-1, -1, -1, 0, 1, 0};
	private final static int[] dy1 = {-1, 0, 1, 1, 0, -1};
	private final static int[] dx2 = {-1, 0, 1, 1, 1, 0};
	private final static int[] dy2 = {0, 1, 1, 0, -1, -1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			System.out.println("Case #" + tc);
			n = sc.nextInt();
			m = sc.nextInt();
			map = new int[m][n];
			visited = new int[m][n];
			res = 0;
			resTemp = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					resTemp = map[i][j];
					int[] temp = getLinkedNode(i, j);
					resTemp += getMaxSumLinked(temp);
					if (res < resTemp) {
						res = resTemp;
					}
				}
			}
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					backTrack(i, j, 1, map[i][j]);
					visited = new int[m][n];
				}
			}
			System.out.println(res * res);
		}
	}
	
	private static int[] getLinkedNode(int row, int col) {
		int[] res = new int[6];
		pos = 0;
		if (col % 2 == 0) {
			for (int i = 0; i < 6; i++) {
				int xx = row + dx1[i];
				int yy = col + dy1[i];
				if (checkOut(xx, yy)) {
					res[pos++] = map[xx][yy];
				}
			}
		} else {
			for (int i = 0; i < 6; i++) {
				int xx = row + dx2[i];
				int yy = col + dy2[i];
				if (checkOut(xx, yy)) {
					res[pos++] = map[xx][yy];
				}
			}
		}
		return res;
	}
	
	private static int getMaxSumLinked(int[] arr) {
		for (int i = 0; i < pos - 1; i++) {
			for (int j = i + 1; j < pos; j++) {
				if (arr[j] > arr[i]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr[0] + arr[1] + arr[2];
	}
	
	private static void backTrack(int row, int col, int count, int sum) {
		if (count == 4) {
			if (res < sum) {
				res = sum;
			}
			return;
		}
		visited[row][col] = 1;
		if (col % 2 == 0) {
			for (int i = 0; i < 6; i++) {
				int xx = row + dx1[i];
				int yy = col + dy1[i];
				if (checkOut(xx, yy) && visited[xx][yy] == 0) {
					visited[xx][yy] = 1;
					backTrack(xx, yy, count + 1, sum + map[xx][yy]);
					visited[xx][yy] = 0;
				}
			}
		} else {
			for (int i = 0; i < 6; i++) {
				int xx = row + dx2[i];
				int yy = col + dy2[i];
				if (checkOut(xx, yy) && visited[xx][yy] == 0) {
					visited[xx][yy] = 1;
					backTrack(xx, yy, count + 1, sum + map[xx][yy]);
					visited[xx][yy] = 0;
				}
			}
		}
	}
	
	private static boolean checkOut(int x, int y) {
		return x >= 0 && x < m &&  y >= 0 && y < n;
	}
}
