import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int[] lengthAds, point, arrivalTime, durationTime, endTime, startTimeAds;
	static boolean[] isUsedAds;
	static int n, res;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
 		for (int tc = 1; tc <= T; tc++) {
 			n = sc.nextInt();
 			lengthAds = new int[3];
 			point = new int[3];
 			startTimeAds = new int[3];
 			isUsedAds = new boolean[3];
 			arrivalTime = new int[n];
 			durationTime = new int[n];
 			endTime = new int[n];
 			res = 0;
 			
 			for (int i = 0; i < 3; i++) {
 				lengthAds[i] = sc.nextInt();
 			}
 			for (int i = 0; i < 3; i++) {
 				point[i] = sc.nextInt();
 			}
 			for (int i = 0; i < n; i++) {
 				arrivalTime[i] = sc.nextInt();
 				durationTime[i] = sc.nextInt();
 				endTime[i] = arrivalTime[i] + durationTime[i];
 			}
 			backTrack(0, 0);
 			System.out.println("Case #" + tc + " " + res);
 		}
	}
	
	private static void backTrack(int indexAds, int timeStart) {
		if (indexAds > 2) {
			getMaxPoint();
			return;
		}
		if (timeStart > 50) return;
		
		for (int i = 0; i < 3; i++) {
			if (!isUsedAds[i]) {
				isUsedAds[i] = true;
				startTimeAds[i] = timeStart;
				backTrack(indexAds + 1, timeStart + lengthAds[i]);
				isUsedAds[i] = false;
			}
		}
		
		backTrack(indexAds, timeStart + 1);
		
	}
	
	private static void getMaxPoint() {
		int maxPoint = 0;
		int[] pointOfVisitor = new int[n];
		for (int i = 0; i < n; i++) {
			pointOfVisitor[i] = 0;
			for (int j = 0; j < 3; j++) {
				if (arrivalTime[i] <= startTimeAds[j] && startTimeAds[j] + lengthAds[j] <= endTime[i]) {
					if (pointOfVisitor[i] < point[j]) {
						pointOfVisitor[i] = point[j];
					}
				}
			}
			maxPoint += pointOfVisitor[i];
		}
		if (res < maxPoint) res = maxPoint;
	}
}
