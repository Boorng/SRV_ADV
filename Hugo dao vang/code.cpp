#include<iostream>
using namespace std;
#define cons 25
int n,so_movang;
int gold_x[cons];
int gold_y[cons];
int arr_map[cons][cons];
int index_gold[cons][cons];
int maxmax,minmin;
bool kiem_tra[cons];
bool kiemtra_temp[cons];
int min_index;
int dem_gold;

int dx[4]={-1,0,1,0};
int dy[4]={0,1,0,-1};

struct Queue
{
	int rear,front;
	int arr_data[100000];
	Queue()
	{
		rear = front = 0;
	}
	bool isEmpty()
	{
		return rear == front;
	}
	void push(int a)
	{
		arr_data[rear] = a;
		rear++;
		rear = rear % 100000;
	}
	int pop()
	{
		int a = arr_data[front];
		front++;
		front = front % 100000;
		return a;
	}
};


void BFS(int vt_hang,int vt_cot)
{
	for (int i = 0; i < so_movang; i++)
	{
		kiemtra_temp[i] = false;
	}
	maxmax = 0;
	dem_gold = 0;
	int check_BFS[cons][cons];
	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n; j++)
		{
			check_BFS[i][j] = -1;
		}
	}
	Queue Q = Queue();
	Q.push(vt_hang);
	Q.push(vt_cot);
	check_BFS[vt_hang][vt_cot] = 0;
	while (!Q.isEmpty())
	{
		int x = Q.pop();
		int y = Q.pop();
		for (int i = 0; i < 4; i++)
		{
			int hang = x + dx[i];
			int cot = y + dy[i];
			if(hang>=1 && hang<=n && cot>= 1 && cot<=n && arr_map[hang][cot] !=0 && check_BFS[hang][cot] == -1)
			{
				check_BFS[hang][cot] = check_BFS[x][y] + 1;
				Q.push(hang);
				Q.push(cot);
				if(arr_map[hang][cot] == 9)
				{
					dem_gold++;
					kiemtra_temp[index_gold[hang][cot]] = true;
					if(check_BFS[hang][cot] > maxmax )
					{
						maxmax = check_BFS[hang][cot];	

					}
				}
			}
		}
	}
}

int main()
{
//	freopen("input.txt","r",stdin);
	int th;cin>>th;
	for (int tc = 1; tc <= th; tc++)
	{
		cin >> n >> so_movang;
		for (int i = 0; i < so_movang; i++)
		{
			cin >> gold_x[i] >> gold_y[i];
			index_gold[gold_x[i]][gold_y[i]] = i;
			kiem_tra[i] = false;

		}
		for (int i = 1; i <= n; i++)
		{
			for (int j = 1; j <= n; j++)
			{
				cin >> arr_map[i][j];
			}
		}
		for (int i = 0; i < so_movang; i++)
		{
			arr_map[gold_x[i]][gold_y[i]] = 9;
		}

		maxmax =0;
		minmin = 999999;
		int sl = 0;
		for (int i = 1; i <= n; i++)
		{
			for (int j = 1; j <= n; j++)
			{
				if(arr_map[i][j] == 1)
				{
					BFS(i,j);
					if(dem_gold > sl)
					{
						sl = dem_gold;
						minmin = maxmax;
						for (int k = 0; k < so_movang; k++)
						{
							kiem_tra[k] = kiemtra_temp[k]; 
						}
					}
					else if(dem_gold == sl)
					{
						if(maxmax != 0)
						{
							if(minmin > maxmax)
							{
								minmin = maxmax;
								for (int k = 0; k < so_movang; k++)
								{
									kiem_tra[k] = kiemtra_temp[k]; 
								}
							}
						}
					}
				}
			}
		}
		cout << "Case #" << tc << endl;
		if(minmin == 999999)
		{
			cout << -1 << endl;
		}
		else
		{
			cout << minmin << endl;
			for (int i = 0; i < so_movang; i++)
			{
				if(kiem_tra[i] == false)
				{
					cout << gold_x[i] << " " << gold_y[i] << endl; 
				}
			}
		}
	}

	return 0;
}
///////////////////////////////////////////////////



#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
using namespace std ; 
int n , g ; 
int G[6][2] ; 
int vsG[6] ; 
int vsGC[6] ; 
int map[25][25] ; 
int vsMap[25][25] ; 
int Tmap[25][25] ; 
int f = -1 ; 
int r = -1 ; 
int Qx[10000]; 
int Qy[10000]; 
int dx[4] = {-1,0,0,1} ; 
int dy[4] = {0,-1,1,0} ; 
int tMin = 999999 ;
int gMAX = 0 ; 
bool checkM = false ; 
int checkHovang(int x , int y)
{
	for(int i = 1 ; i <= g ; i++)
	{
		if(x == G[i][0] && y == G[i][1] )
		{
			return i ; 
		}
	}
	return -1; 
}
int bfs(int x , int y )
{
	int gcl = g ; 
	f = r = -1 ; 
	vsMap[x][y] = 1 ; 
	Qx[++r]= x ; 
	Qy[r] = y ; 
	while(f != r)
	{
		x = Qx[++f] ; 
		y = Qy[f] ; 

		for(int i = 0 ; i < 4 ; i++)
		{
			int xx = x + dx[i] ; 
			int yy = y + dy[i] ; 

			if(xx >= 1 && xx <= n && yy >= 1 && yy <= n)
			{
				if(vsMap[xx][yy] == 0 && map[xx][yy] == 1)
				{
					int hv = checkHovang(xx,yy) ; 
					if(hv != -1 )
					{
						vsG[hv] = 1 ; 
						gcl -- ; 
						if(gcl == 0)
						{
							vsMap[xx][yy] = 1 ;
							Tmap[xx][yy] += Tmap[x][y] + 1 ;
							return Tmap[x][y] + 1 ; 
						}
					}
					vsMap[xx][yy] = 1 ; 
					Tmap[xx][yy] += Tmap[x][y] + 1 ; 
					Qx[++r]= xx ; 
					Qy[r] = yy ; 
				}
			}
		}
	}
	if(gcl < g)
	{
		int t = 0 ; 
		for(int i = 1 ; i <= g ; i++)
		{
			if(vsG[i] == 1)
			{
				if(t < Tmap[G[i][0]][G[i][1]])
				{
					t = Tmap[G[i][0]][G[i][1]] ;
				}
			}

		}
		return t ; 
	}
	else return 0 ; 
}
void reset()
{
	for(int i = 1 ; i <= g ; i++)
	{
		vsG[i] = 0 ; 
	}
	for(int i = 1 ; i <= n ; i++)
	{
		for(int j = 1 ; j <= n ; j++)
		{
			Tmap[i][j] = 0 ; 
			vsMap[i][j] = 0 ; 
		}
	}
}
int main()
{
	//freopen("input.txt" , "r" , stdin) ; 
	int T ; 
	cin >> T ; 
	for(int tc = 1 ; tc <= T ; tc ++)
	{
		cin >> n >> g ; 
		for(int i = 1 ; i <= g ; i++)
		{
			vsGC[i] = 0 ; 
		}
		for(int i = 1 ; i <= g ; i++)
		{
			vsG[i] = 0 ; 
			cin >> G[i][0] >> G[i][1] ; 
		}

		for(int i = 1 ; i <= n ; i++)
		{
			for(int j = 1 ; j <= n ; j++)
			{
				cin >> map[i][j] ; 
			}
		}
		int count ; 
		tMin = 999999 ; 
		gMAX = g ; 
		checkM = false ; 
		while(gMAX > 0 )
		{
			for(int i = 1 ; i <= n ; i++)
			{
				for(int j = 1 ; j <= n ; j++)
				{
					reset() ; 
					if(map[i][j] != 0 && checkHovang(i,j) == -1)
					{
						int t = bfs(i,j) ;
						count = 0 ; 
						for(int k = 1 ; k <= g ; k++)
						{
							if(vsG[k] == 1) 
							{
								count ++ ; 
							}
						}
						if(gMAX == count)
						{
							checkM = true ; 
							if(tMin > t && t != 0 ) 
							{
								tMin = t ; 
								for(int k = 1 ; k <= g ; k++)
								{
									vsGC[k] = vsG[k] ; 
								}
							}
						}
					}
				}
			}
			if(checkM)
			{
				break ; 
			}
			gMAX -- ; 
		}
		count = 0 ; 
		for(int k = 1 ; k <= g ; k++)
		{
			if(vsGC[k] == 1) 
			{
				count ++ ; 
			}
		}
		cout << "Case #" << tc << endl ; 
		if(count == 0)
		{
			cout << -1 << endl; 
		}
		else if(count == g)
		{
			cout << tMin << endl  ; 
		}
		else 
		{
			cout << tMin << endl  ; 
			for(int k = 1 ; k <= g ; k++)
			{
				if(vsGC[k] == 0) 
				{
					cout << G[k][0] << " " << G[k][1] << endl ; 
				}
			}
		}

	}
	return 0 ; 
}
