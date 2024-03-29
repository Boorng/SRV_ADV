package PT07Y;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	
	private static int res;
	private static int[][] map;
	private static int[] visited;
	private static int m, n, count;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("D:\\AlgorithmSRV\\DFS\\src\\PT07Y\\input.txt"));
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][n];
		visited = new int[n];
		res = 0;
		count = 0;
		for (int i = 0; i < m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			map[u-1][v-1] = 1;
			map[v-1][u-1] = 1;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		DFS(0);
		
		System.out.println(count == n ? "ĐÚNG" : "SAI");
	}
	
	private static void DFS(int node) {
		visited[node] = 1;
		count++;
		for (int i = 0; i < n; i++) {
			if (map[node][i] == 1 && visited[i] == 0) {
				DFS(i);
			}
		}
	}
}
