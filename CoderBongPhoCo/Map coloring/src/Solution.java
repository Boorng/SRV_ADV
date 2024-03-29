import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int countries, borders;
	static int[][] map;
	static boolean check;
	static int[] color;
	static int[] Queue;
	static boolean[] isUsed;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			countries = sc.nextInt();
			borders = sc.nextInt();
			map = new int[1001][1001];
			Queue = new int[10000];
			check = true;
			color = new int[1001];
			isUsed = new boolean[1001];
			color[0] = 0;
			for (int i = 0; i < borders; i++) {
				int x = sc.nextInt() - 1;
				int y = sc.nextInt() - 1;
				map[x][y] = 1;
				map[y][x] = 1;
			}
			BFS (0);
			if (!check) {
				System.out.println("#" + tc + " -1");
				continue;
			}
			System.out.print("#" + tc + " ");
			for (int i = 0; i < countries; i++) {
				System.out.print(color[i]);
			}
			System.out.println();
		}
	}
	
	private static void BFS(int index) {
		int front = 0;
		int rear = 0;
		Queue[rear++] = index;
		isUsed[index] = true;
		while (front != rear) {
			int x = Queue[front++];
			for (int i = 0; i < countries; i++) {
				if (map[x][i] == 1) {
					if (!isUsed[i]) {
						isUsed[i] = true;
						color[i] = 1 - color[x];
						Queue[rear++] = i;
					} else {
						if (color[i] == color[x]) {
							check = false;
						}
					}
				}
			} 
		}
	}
}
