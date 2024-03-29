import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	private static int numIsland, numElectricIsland, numLinked, res;
	private static int[][] weight;
	private static int[] havaElectric;
	private static int[] strong;
	private static int[] Queue;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			numIsland = sc.nextInt();
			numElectricIsland = sc.nextInt();
			numLinked = sc.nextInt();
			weight = new int[numIsland][numIsland];
			havaElectric = new int[numElectricIsland];
			strong = new int[numIsland];
			Queue = new int[10000];
			
			for (int i = 0; i < numElectricIsland; i++) {
				havaElectric[i] = sc.nextInt();
			}
			for (int i = 0; i < numLinked; i++) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				weight[u][v] = 1;
				weight[v][u] = 1;
 			}
			for (int i = 0; i < numIsland; i++) {
				strong[i] = Integer.MAX_VALUE;
			}
			for (int i = 0; i < numElectricIsland; i++) {
				Queue = new int[10000];
				BFS(havaElectric[i]);
			}
			res = idMaxArr(strong);
			System.out.println(res);
		}
	}
	
	private static void BFS(int idIsland) {
		int front = 0;
		int rear = 0;
		Queue[rear++] = idIsland;
		strong[idIsland] = 0;
		while (front != rear) {
			int id = Queue[front++];
			for (int i = 0; i < numIsland; i++) {
				if ((weight[id][i] == 1) && strong[i] > strong[id] + 1) {
					strong[i] = strong[id] + 1;
					Queue[rear++] = i;
				}
			}
		}
	}
	
	private static int idMaxArr(int[] arr) {
		int max = 0;
		int id = -1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
				id = i;
			}
		}
		return id;
	}
}
