import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	
	private static int numberSpot, ans;
	private static int[] positions, customers;
	private static boolean[] visited;
	private final static int[][] gate = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			System.out.println("Case #" + tc);
			numberSpot = sc.nextInt();
			positions = new int[3];
			customers = new int[3];
			visited = new boolean[numberSpot];
			ans = Integer.MAX_VALUE;
			
			for (int i = 0; i < 3; i++) {
				positions[i] = sc.nextInt() - 1;
				customers[i] = sc.nextInt();
			}
			
			for (int i = 0; i < 6; i++) {
				visited = new boolean[numberSpot];
				backTrack(0, gate[i], visited, 0);
			}			
			System.out.println(ans);
		}
	}
	
	private static void backTrack(int indexGate, int[] typeGate, boolean[] visited, int res) {
		if (indexGate > 2) {
			return;
		}
		int idGate = typeGate[indexGate];
		int left = positions[idGate];
		int right = positions[idGate];
		int mid = positions[idGate];
		int countCustomer = 0;
		
		Stack<Integer> stack = new Stack<Integer>();
		if (!visited[mid]) {
			visited[mid] = true;
			stack.push(mid);
			res++;
			countCustomer++;
			if (countCustomer == customers[idGate]) {
				backTrack(indexGate + 1, typeGate, visited, res);
			}
		}

		while (countCustomer < customers[idGate]) {
			if (left > 0) left--;
			if (right < numberSpot - 1) right++;
			if (!visited[left] && !visited[right] && mid - left == right - mid && customers[idGate] - countCustomer == 1) {
				res += mid - left + 1;
				visited[left] = true;
				backTrack(indexGate + 1, typeGate, visited, res);
				visited[left] = false;
				visited[right] = true;
				backTrack(indexGate + 1, typeGate, visited, res);
				visited[right] = false;
				countCustomer++;
			} else {
				if (!visited[left]) {
					res += mid - left + 1;
					visited[left] = true;
					stack.push(left);
					countCustomer++;
				}
				if (!visited[right]) {
					res += right - mid + 1;
					visited[right] = true;
					stack.push(right);
					countCustomer++;
				}	
				if (countCustomer == customers[idGate]) {
					backTrack(indexGate + 1, typeGate, visited, res);
				}
			}
		}
		while (stack.size() != 0) {
			visited[(int) stack.peek()] = false;
			stack.pop();
		}
		if (indexGate == 2) {
			if (ans > res) {
				ans = res;
			}
			return;
		}
	}
}
