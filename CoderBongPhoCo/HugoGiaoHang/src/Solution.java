import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int Sx, Sy, Hx, Hy, N, res;
	static int[][] Point;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			Sx = sc.nextInt();
			Sy = sc.nextInt();
			Hx = sc.nextInt();
			Hy = sc.nextInt();
			N = sc.nextInt();
			Point = new int[N+1][2];
			Point[0][0] = Sx;
			Point[0][1] = Sy;
			visited = new boolean[N+1];
			res = Integer.MAX_VALUE;
			for (int i = 1; i <= N; i++) {
				Point[i][0] = sc.nextInt();
				Point[i][1] = sc.nextInt();
			}
			//visited[0] = true;
			backTrack(0, 0, 0);
			System.out.println(res);
		}
	}
	
	private static void backTrack(int index,int count, int dis) {
		if (count == N) {
			int temp = dis + distance(Hx, Hy, Point[index][0], Point[index][1]);
			if (res > temp) {
				res = temp;;
			}
			return;
		}
		if (res < dis + distance(Hx, Hy, Point[index][0], Point[index][1])) return;
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				backTrack(i, count + 1, dis + distance(Point[index][0], Point[index][1], Point[i][0], Point[i][1]));
				visited[i] = false;
			}
		}
	}
	
	private static int distance(int x1, int y1, int x2, int y2) {
		return (int) (Math.abs(x1 - x2) + Math.abs(y1 - y2));
	}
}
