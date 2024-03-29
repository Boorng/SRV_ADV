import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int n, whites, blues;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			map = new int[n][n];
			whites = 0;
			blues = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			backTrack(0, 0, n);
			System.out.println(whites + " " + blues);
		}
	}
	
	private static void backTrack(int xStart, int yStart, int size) {
		if (check(xStart, yStart, size) == 0) {
			whites++;
			return;
		} else if (check(xStart, yStart, size) == 1) {
			blues++;
			return;
		}
		backTrack(xStart, yStart, size/2);
		backTrack(xStart, yStart+size/2, size/2);
		backTrack(xStart+size/2, yStart, size/2);
		backTrack(xStart+size/2, yStart+size/2, size/2);
	}
	
	private static int check(int xStart, int yStart, int size) {
		int white = 0;
		int blue = 0;
		for (int i = xStart; i < xStart+size; i++) {
			for (int j = yStart; j < yStart+size; j++) {
				if (map[i][j] == 0) {
					white++;
				} else {
					blue++;
				}
			}
		}
		if (blue == 0) return 0;
		if (white == 0) return 1;
		return -1;
	}
}
