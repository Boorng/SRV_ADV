import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	private static int soCong, res;
	private static int[][] thongTinCong;
	private static int[][] thongTinLinhThue;
	private static int[] checkCong;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
			
		for (int tc = 1; tc <= T; tc++) {
			res = Integer.MAX_VALUE;
			soCong = sc.nextInt();
			thongTinCong = new int[28][2];
			thongTinLinhThue = new int[28][2];
			checkCong = new int[28];
			for (int i = 0; i < soCong; i++) {
				thongTinCong[i][0] = sc.nextInt();
				thongTinCong[i][1] = sc.nextInt();
			}
			backTrack(0, 0);
			System.out.println("Case #" + tc + " " + res);
		}
	}
	
	private static void backTrack(int indexCong, int sum) {
		if (indexCong == soCong) {
			if (sum < res) {
				res = sum;
			}
			return;
		}
		if (sum > res) return;
		
		//3 status
		for (int status = 1; status <= 3; status++) {
			if (status == 1) {
				backTrack(indexCong + 1, sum + thongTinCong[indexCong][1]);
			}
			if (status == 2) {
				thongTinLinhThue[indexCong][0] = thongTinCong[indexCong][0];
				thongTinLinhThue[indexCong][1] = 3;
				checkCong[indexCong] = 1;
				backTrack(indexCong + 1, sum + 2 * thongTinCong[indexCong][1]);
				thongTinLinhThue[indexCong][0] = 0;
				thongTinLinhThue[indexCong][1] = 0;
				checkCong[indexCong] = 0;
			}
			if (status == 3) {
				int quanTa = 0;
				for (int i = 0; i < indexCong; i++) {
					if (checkCong[i] == 1) {
						quanTa += thongTinLinhThue[i][0];
					}
				}
				int quanDich = thongTinCong[indexCong][0];
				if (quanDich > quanTa) {
					return;
				}
				int[] copCheckCong = new int[28];
				int[][] copThongLinhThue = new int[28][2];
				for (int i = 0; i < indexCong; i++) {
					copCheckCong[i] = checkCong[i];
					copThongLinhThue[i][0] = thongTinLinhThue[i][0];
					copThongLinhThue[i][1] = thongTinLinhThue[i][1];
				}
				
				for (int i = 0; i < indexCong; i++) {
					if (checkCong[i] == 1) {
						if (thongTinLinhThue[i][0] <= quanDich) {
							quanDich -= thongTinLinhThue[i][0];
							thongTinLinhThue[i][0] = 0;
						} else {
							thongTinLinhThue[i][0] -= quanDich;
							quanDich = 0;
							break;
						}
					}
				}
				for (int i = 0; i < indexCong; i++) {
					if (checkCong[i] == 1) {
						thongTinLinhThue[i][1]--;
						if (thongTinLinhThue[i][1] == 0 || thongTinLinhThue[i][0] == 0) {
							thongTinLinhThue[i][0] = 0;
							thongTinLinhThue[i][1] = 0;
							checkCong[i] = 0;
						}
					}
				}
				backTrack(indexCong + 1, sum);
				for (int i = 0; i < indexCong; i++) {
					checkCong[i] = copCheckCong[i];
					thongTinLinhThue[i][0] = copThongLinhThue[i][0];
					thongTinLinhThue[i][1] = copThongLinhThue[i][1];
				}
			}
		}
	}
}
