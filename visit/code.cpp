#include<iostream>
#include<stdio.h>
using namespace std;
int n,e,k,t,kt; // n : so phong bam; e: so luong mui ten ; k: thoi gian kang den; t: so phut; kt = thoi gian /10 so doan
int A[101][201]; // mang 
float B[101][201]; // mang luu xac suat
float N[101];
float M[101][101]; // mang thoi gian
int check[101][101];
int Qx[1000000];
float Qy[1000000];
int Qd[10000000];
int f,r;

void reset(){
	for(int i=1;i<=101;i++){
		for(int j=1;j<=101;j++){
			 A[i][j]=0;
			B[i][j]=0;
			 N[i]=0;
			 M[i][j]=0;
			 check[i][j]=0;
		}
	}
}
void BFS(){
	Qx[f]=1;
	M[1][0]=1;
	Qd[f++]=0;
	check[1][0]=0;
	while(f!=r){
		int a=Qx[r];
		int c=Qd[r++];
		for(int i=1;i<=A[a][0];i++){
			if(c+1<=kt){
				if(check[A[a][i]][c+1]==0){
					check[A[a][i]][c+1]=1;
					Qx[f]=A[a][i];
					M[A[a][i]][c+1]+=M[a][c]*B[a][i];
					Qd[f++]=c+1;
				}
				else {
					M[A[a][i]][c+1]+=M[a][c]*B[a][i];
				}
			}

		}
	}
}
int main(){
	//freopen("Text.txt","r",stdin);
	for(int tc=1;tc<=10;tc++){
		reset();
		cin>>n>>e>>k>>t;
		int x,y; // vitri bat dau y diem di den
		float z;// xac suat
		for(int i=1;i<=e;i++){
			cin>>x>>y;
			scanf("%f",&z);
			A[x][0]++;
			A[x][A[x][0]]=y;
			B[x][A[x][0]]=z;
		}
		kt=t/10;
		BFS();
		float maxx=0;
		int vt=1;
		for(int i=1;i<=n;i++){
			if(M[i][kt]>maxx){
				maxx=M[i][kt];
				vt=i;
			}
		}
		if(maxx==0) vt=0;
		kt=(t-k)/10;
		float maxx2=0;
		int vt2=1;
		for(int i=1;i<=n;i++){
			if(M[i][kt]>maxx2){
				maxx2=M[i][kt];
				vt2=i;
			}
		}
		if(maxx==0) vt=0;
		cout<<"#"<<tc<<" "<<vt<<" ";
		printf("%0.6f", maxx);
		cout<<" "<<vt2<<" ";
		printf("%0.6f", maxx2);
		cout<<endl;
	}
	
	return 0;
}

#include <iostream>
using namespace std;
int N, E, K, T, nStep;
float matrix[15][15];
float timeline[15][15];


struct Point {
	int row, col;
};

Point q[1000000];
int front, rear;

void init(){
	front == rear;
}

void push(Point p){
	q[rear++] = p;
}

bool isEmpty(){
	return front == rear;
}
Point pop(){
	return q[front++];
}


int visit[105];

void BFS(int index){
	init();
	for(int i = 1; i <= N; i++){
		if(timeline[i][index-1] != 0){
			Point tmp; tmp.row = i; tmp.col = index - 1;
			push(tmp);
		}
		visit[i] = 0;
	}
	while(!isEmpty()){
		Point p = pop();
		int hang = p.row;
		int cot = p.col;
		for(int i = 1; i <= N; i++){
			if(matrix[hang][i] != 0){
				timeline[i][index] += timeline[hang][index-1] * matrix[hang][i];
			}
		}
	}	
}


int main(){
	freopen("Text.txt", "r", stdin);
	for(int tc = 1; tc <= 5; tc++){
		cin >> N >> E >> K >> T;
		for(int i = 0; i < E; i++){
				int x, y;
				float z;
				cin >> x >> y >> z;
				matrix[x][y] = z;
		}
		nStep = T / 10;
		for(int i = 0; i <= N; i++){
			for(int j = 0; j <= nStep; j++){
				timeline[i][j] = 0;
				if(i == 1 && j == 0)
					timeline[i][j] = 1;
			}
		}

		for(int i = 1; i <= nStep; i++){
				BFS(i);
		}
		int res1 = 0, res2 = 0;
		float xs1, xs2;
		float max = 0;
		float max1 = 0;
		int tKang = nStep - K / 10;
		for(int i = 1; i <= N; i++){
			if(timeline[i][nStep] > max){
				max = timeline[i][nStep];
				res1 = i;
			}
			if(tKang > 1 && timeline[i][tKang] > max1){
				max1 = timeline[i][tKang];
				res2 = i;
			}
		}
		cout.precision(6);
		cout << "#" << tc << " ";
		if(tKang <= 1){
			cout << res1 << " " <<fixed<< max << " " << "1" << " 1.000000" << endl; 
		}
		else 
			cout << res1 << " " <<fixed << max << " " << res2 << " " << max1 << endl;

	}
	return 0;
}

