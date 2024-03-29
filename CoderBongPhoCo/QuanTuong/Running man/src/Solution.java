import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int M, D, res;
	static int[][] typeRun;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			System.out.println("Case #" + tc);
			M = sc.nextInt();
			D = sc.nextInt();
			res = Integer.MAX_VALUE;
			typeRun = new int[5][2];
			for (int i = 0; i < 5; i++) {
				int phut = sc.nextInt();
				int giay = sc.nextInt();
				typeRun[i][0] = phut * 60 + giay;
				typeRun[i][1] = sc.nextInt();
			}
			backTrack(0, 0, 0, 0);
			
			System.out.println(res == Integer.MAX_VALUE ? -1 : res/60 + " " + res%60);
		}
	}
	
	private static void backTrack(int type, int energy, int dis, int step) {
		if (type == 4) {
			int tempRes = dis + (D-step)*typeRun[type][0];
			int tempEnergy = energy + (D-step)*typeRun[type][1];
			if (tempEnergy <= M) {
				if (res > tempRes) {
					res = tempRes;
				}
				return;
			}
		}
		if (dis > res || energy > M) return;
		for (int i = 0; i <= D - step; i++) {
			if (type < 4) {
				backTrack(type + 1, energy + i*typeRun[type][1], dis + i*typeRun[type][0], step + i);
			}
		}
	}
}
