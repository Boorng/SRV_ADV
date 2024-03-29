import java.util.Scanner;




public class Solution {

	public static boolean[][] visit;

	static int[][][] arr;

	static Point [] Q;
	static Point [] GD;
	static int[] X = { -1, 0, 0,1};
	static int[] Y = { 0, 1, -1,0};
	static int [][]a;
	

	static int n, answer, m, l, r, dem, sums;

	static int kq;
	static boolean check;

	public static void main(String[] args)  {

		Scanner in = new Scanner(System.in);

		int T = in.nextInt();
	
		Q= new Point[10000000];
		for (int tc = 1; tc <= T; tc++) {
			//if (tc== 48) {
			
			n = in.nextInt();
			m = in.nextInt();
			arr = new int[n + 1][n + 1][5];
			
			visit = new boolean[n + 1][n + 1];
			a = new int [n+1][n+1];
			GD = new Point[5];
			for (int i = 0; i < m; i++) {
				GD[i]= new Point(in.nextInt(), in.nextInt(), 0, 0);
			}
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					arr[i][j][0] = in.nextInt()-2;
					visit[i][j] = false;
					a[i][j] = arr[i][j][0];
					for (int j2 = 1; j2 <=m; j2++) {
						arr[i][j][j2] = -1;
					}
				}
			}

				
			answer = 0;
			for (int i = 0; i < m; i++) {
				Bfs(new Point(GD[i].x, GD[i].y, 0,0), i+1);
			}
			
//			for (int k = 1; k <=m; k++) {
//			for (int i = 1; i <= n; i++) {
//				for (int j = 1; j <= n; j++) {
//					 System.out.print(arr[i][j][k]+" ");
//					}
//				System.out.println();
//				}
//			System.out.println();
//			}
			
			
			int movang = 0;
			int xx =0;
			int yy = 0;
			int maxs= 0;
		
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (arr[i][j][0] == -1 ) {
						int max= 0;
						
						int dem = 0;
					for (int k = 1; k <= m; k++) {
						if (arr[i][j][k] > 0) {
							if (arr[i][j][k]> max) {
								max = arr[i][j][k];
							}
							dem ++;
						}
					}
					
					if (dem!= 0 && dem >= movang) {
						if (dem == movang) {
							if (max < maxs) {
								xx= i;
								yy = j;
								maxs = max;
							}
						}else {
							movang = dem;
							xx= i;
							yy = j;
							maxs = max;
						}
					  }
					}
				}
			}
			
			int max = 0;
			
			for (int i = 1; i <= m; i++) {
				if (arr[xx][yy][i] > max ) {
					max = arr[xx][yy][i];
				}
			}
			
			
			System.out.println("Case #" + tc);
			if (movang == 0) {
				System.out.println(-1);
			}
			
			else {
				System.out.println(maxs);
				for (int i = 1; i <= m; i++) {
					if (arr[xx][yy][i] < 0) {
						System.out.println(GD[i-1].x+" "+ GD[i-1].y);
					}
				}
			}

		}
	}
	
	private static void Bfs(Point p, int k) {
		//System.out.println(p.x+" "+ p.y);
		l = r = 0;
		Q[l]= p;
		l++;
		int kq=0;
		arr[p.x][p.y][k] = p.d;
		
		while (l!= r) {
			Point point = Q[r];
			r++;
			//System.out.println(point.x+" "+ point.y);
			int d = point.d;
					for (int j = 0; j < 4; j++) {
							int xx = point.x + X[j];
							int yy = point.y + Y[j];
								if (xx > 0 && yy > 0 && xx <=n && yy <= n) {
								if (arr[xx][yy][0] == -1) {
									if (arr[xx][yy][k]==-1) {
										arr[xx][yy][k] = d+1;
										Q[l]= new Point(xx, yy, d+1, 0);
										l++;
								}
									
							}
						}
				}
			}
	 }
}

class Point{
	int x, y, d, s;

	public Point(int x, int y, int d, int s) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
		this.s = s;
	}
	
}
