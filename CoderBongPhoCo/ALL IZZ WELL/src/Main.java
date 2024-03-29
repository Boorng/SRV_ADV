import java.util.Scanner;

public class Main {
	
	private static char[][] map;
	private static boolean[][] visited;
	private final static int[] dx = {1,1,1, 0, 0, -1, -1,-1};
	private final static int[] dy = {1,-1,0,1,-1, 1, -1, 0};
	private final static char[] target = {'A', 'L', 'L', 'I', 'Z', 'Z', 'W', 'E', 'L', 'L'}; 
	private static int res, m, n, check;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			m = sc.nextInt();
			n = sc.nextInt();
			map = new char[m][n];
			visited = new boolean[m][n];
			res = 0;
			String[] strings = new String[m];
			for (int i = 0; i < m; i++) {
				strings[i] = sc.next();
				for (int j = 0; j < n; j++) {
					map[i][j] = strings[i].charAt(j);
				}
			}
			DFS(0, 9, 1);
			System.out.println(res > 0 ? "YES" : "NO");
		}
	}
	
	private static void DFS(int row, int col, int count) {
		if (count == 10) {
			res++;
			return;
		}
		visited[row][col] = true;
		for (int i = 0; i < 8; i++) {
			int x = row + dx[i];
			int y = col + dy[i];
			if (x >= 0 && x < m && y >= 0 && y < n && map[x][y] == target[count] && !visited[row][col]) {
				check++;
				DFS(x, y, count + 1);
				if (res > 0) break;
			}
		}
		visited[row][col] = false;
	}
}
