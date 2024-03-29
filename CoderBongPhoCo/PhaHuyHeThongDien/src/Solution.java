import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int n, res;
	static int[][] map;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			map = new int[101][101];
			visited = new boolean[101];
			res = -1;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			int[][] cop = copMap(map);
			int max = 2;
			for (int i = 0; i < n; i++) {
				visited = new boolean[101];
				setUp(i);
				int count = countRegion();
				if (max < count) {
					max = count;
					res = i;
				}
				map = copMap(cop);
			}
			System.out.println(res == -1 ? 0 : (res + 1));
		}
	}
	
	private static int[][] copMap(int[][] map) {
		int[][] res = new int[101][101];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				res[i][j] = map[i][j];
			}
		}
		return res;
	}
 	
	private static void setUp(int index) {
		for (int i = 0; i < n; i++) {
			if (map[index][i] == 1) {
				map[index][i] = 0;
				map[i][index] = 0;
			}
		}
	}
	
	private static int countRegion() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				DFS(i);
				count++;
			}
		}
		return count;
	}
	
	private static void DFS(int index) {
		visited[index] = true;
		for (int i = 0; i < n; i++) {
			if (map[index][i] == 1 && !visited[i]) {
				DFS(i);
			}
		}
	}
}
