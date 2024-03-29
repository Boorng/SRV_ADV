import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int soLa, res;
	static int[] xLa, yLa, rLa, saveStep, visited;
 	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			soLa = sc.nextInt();
			xLa = new int[soLa];
			yLa = new int[soLa];
			rLa = new int[soLa];
			visited = new int[soLa];
			saveStep = new int[soLa];
			res = -1;
			for (int i = 0; i < soLa; i++) {
				xLa[i] = sc.nextInt();
				yLa[i] = sc.nextInt();
				rLa[i] = sc.nextInt();
				saveStep[i] = Integer.MAX_VALUE;
			}
			
			int indexLa = 0;
			visited[indexLa] = 1;
			saveStep[indexLa] = 0;
			while (indexLa != soLa - 1) {
				for (int i = 0; i < soLa; i++) {
					if (saveStep[i] > caculate(indexLa, i) + saveStep[indexLa]) {
						saveStep[i] = caculate(indexLa, i) + saveStep[indexLa];
					}
				}
				indexLa = getIndexMinStep();
				visited[indexLa] = 1;
			}
			
			if (saveStep[soLa - 1] >= 100000) {
				System.out.println(res);
				continue;
			}
			System.out.println(saveStep[soLa - 1]/500 + " " + saveStep[soLa - 1]%500);
		}
	}
	
	private static int getIndexMinStep() {
		int index = -1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < soLa; i++) {
			if (visited[i] == 0 && min > saveStep[i]) {
				min = saveStep[i];
				index = i;
			}
		}
		return index;
	}
	
	private static int caculate(int x, int y) {
		int tmp = (int) (Math.pow(xLa[x] - xLa[y], 2) + Math.pow(yLa[x] - yLa[y], 2));
		if (tmp <= Math.pow(40 + rLa[x] + rLa[y], 2)) {
			return 1;
		}
		if (tmp > Math.pow(90 + rLa[x] + rLa[y], 2)) {
			return 100000;
		}
		return 500;
	}
}
