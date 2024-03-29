import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int n, m, res;
	static int[][] map;
	static int[] visited;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			m = sc.nextInt();
			map = new int[m][m];
			visited = new int[n];
			res = Integer.MAX_VALUE;
			for (int i = 0; i < m; i++) {
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				map[a][b] = 1;
			}
			backTrack(0);
			System.out.println(res);
		}
	}
	
	private static void backTrack(int x) {
		if (visited[0] == 1 && visited[1] == 1) {
			int temp = count();
			if (res > temp) {
				res = temp;
			}
			return;
		}
		for (int i = 0; i < n; i++) {
			if (map[x][i] == 1) {
				if (visited[i] == 0 && visited[1] == 0) {
					visited[i]++;
					backTrack(i);
					visited[i]--;
				} else if (visited[i] < 2 && visited[1] == 1) {
					visited[i]++;
					backTrack(i);
					visited[i]--;
				}
			}
		}
	}
	
	private static int count() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (visited[i] > 0) count++;
		}
		return count;
	}
}
