import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int n, res;
	static int[][] map;
	static int[] color, visited, check;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			System.out.println("Case #" + tc);
			n = sc.nextInt();
			map = new int[n][n];
			color = new int[n];
			visited = new int[n];
			check = new int[n];
			
			for (int i = 0; i < n; i++) {
				color[i] = 4;
 				for (int j = 0; j < n; j++) {
 					map[i][j] = sc.nextInt();
 				}
			}
			res = 0;
			backTrack(0);
			System.out.println(res);
		}
	}
	
	private static boolean check(int index, int c) {
		for (int i = 0; i < n; i++) {
			if (map[index][i] == 1 && color[i] == c) {
				return false;
			}
		}
		return true;
	}
	
	private static void backTrack(int index) {
		if (index == n) {
			res++;
			return;
		}
		for (int i = 1; i <= 4; i++) {
			if (check(index, i) && visited[index] == 0) {
				visited[index] = 1;
				color[index] = i;
				backTrack(index + 1);
				visited[index] = 0;
				color[index] = 0;
			}
		}
	}
}
