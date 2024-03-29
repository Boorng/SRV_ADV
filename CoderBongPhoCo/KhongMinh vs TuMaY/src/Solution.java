import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	private static int numWall, res, pos;
	private static Wall[] listWall;
	private static int[][] weightMap;
	private static int[] arrWall;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			System.out.println("Case #" + tc);
			numWall = sc.nextInt();
			listWall = new Wall[numWall];
			weightMap = new int[numWall][numWall];
			res = 0;
			arrWall = new int[numWall];
			pos = 0;
			for (int i = 0; i < numWall; i++) {
				int id = sc.nextInt();
				int weight = sc.nextInt();
				int num = sc.nextInt();
				int[] list = new int[num];
				for (int j = 0; j < num; j++) {
					list[j] = sc.nextInt();
				}
				listWall[i] = new Wall(id, weight, num, list);
			}
			sortWall();
			for (int i = 0; i < numWall; i++) {
				for (int j = 0; j < numWall; j++) {
					if (j == i) {
						continue;
					}
					weightMap[i][j] = listWall[i].getWeight() + listWall[j].getWeight();
				}
			}
			for (int i = 0; i < numWall - 1; i++) {
				arrWall[pos++] = i;
				arrWall[pos++] = i + 1;
				if (DFScheckCycle(0, arrWall)) {
					res += weightMap[i][i+1];
					pos -= 2;
				}
			}
			System.out.println(res);
		}
	}
	
	private static boolean DFScheckCycle(int child, int[] arr) {
		boolean[] visited = new boolean[numWall];
		visited[child] = true;
		for (int i = 0; i < pos; i++) {
			if (weightMap[child][i] != 0 && (!visited[i] || i == child)) {
				if (i == child) {
					return true;
				}
				DFScheckCycle(i, arr);
			}
		}
		return false;
	}
	
	private static void sortWall() {
		for (int i = 0; i < numWall - 1; i++) {
			for (int j = i + 1; j < numWall; j++) {
				if (listWall[j].getWeight() > listWall[i].getWeight()) {
					swapWall(listWall[i], listWall[j]);
				}
			}
		}
	}
	
	private static void swapWall(Wall wall1, Wall wall2) {
		int tempId = wall1.getIdWall();
		int tempWeight = wall1.getWeight();
		int tempNumLinked = wall1.getNumLinked();
		int[] tempList = wall1.getListLinked();
		wall1.setIdWall(wall2.getIdWall());
		wall1.setWeight(wall2.getWeight());
		wall1.setNumLinked(wall2.getNumLinked());
		wall1.setListLinked(wall2.getListLinked());
		wall2.setIdWall(tempId);
		wall2.setWeight(tempWeight);
		wall2.setNumLinked(tempNumLinked);
		wall2.setListLinked(tempList);
	}
}

class Wall {
	private int idWall;
	private int weight;
	private int numLinked;
	private int[] listLinked;
	public Wall(int id, int weight2, int num, int[] list) {
		// TODO Auto-generated constructor stub
		this.idWall = id;
		this.weight = weight2;
		this.numLinked = num;
		this.listLinked = list;
	}
	public int getIdWall() {
		return idWall;
	}
	public void setIdWall(int idWall) {
		this.idWall = idWall;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getNumLinked() {
		return numLinked;
	}
	public void setNumLinked(int numLinked) {
		this.numLinked = numLinked;
	}
	public int[] getListLinked() {
		return listLinked;
	}
	public void setListLinked(int[] listLinked) {
		this.listLinked = listLinked;
	}
}
