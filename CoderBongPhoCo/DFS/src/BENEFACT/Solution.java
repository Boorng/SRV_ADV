package BENEFACT;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	private static int n, res, index;
	private static int[][] map;
	private static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("D:\\AlgorithmSRV\\DFS\\src\\BENEFACT\\input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			map = new int[n][n];
			visited = new boolean[n];
			res = 0;
			index = 0;
			for (int i = 0; i < n - 1; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				int distance = sc.nextInt();
				map[start-1][end-1] = distance;
				map[end-1][start-1] = distance;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		for (int i = 0; i < n; i++) {
			DFS(i, 0);
		}
		System.out.println(res + " " + index);
	}
	
	private static void DFS(int node, int dis) {
		if (res < dis) {
			res = dis;
			index = node;
		}
		visited[node] = true;
		for (int i = 0; i < n; i++) {
			if (map[node][i] > 0 && !visited[i]) {
				DFS(i, dis + map[node][i]);
			}
		}
		visited[node] = false;
	}
}
