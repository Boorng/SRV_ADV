#include <iostream>
#pragma warning (disable:4996)
using namespace std;
int map[101][101];
int M , N, answer, cntDust;
int dust[101][101];
int matrix[30][30];
int visitBackTrack[100];
int visitBFS[100][100];
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};
struct Point {
	int row, col;
};
Point toaDo[1000];
Point q[10000];
int front, rear;
void initQ(){
	front = 0;
	rear = 0;
}
bool isEmpty(){
	return front == rear;
}

void push(Point p){
	q[rear] = p;
	rear++;
}

Point pop(){
	Point p = q[front];
	front++;
	return p;
}

void BFS(int start){
	initQ();
	for(int i = 0; i < M; i++){
		for(int j = 0; j < N; j++){
			visitBFS[i][j] = -1;
		}
	}
	push(toaDo[start]);
	visitBFS[toaDo[start].row][toaDo[start].col] = 0;
	while(!isEmpty()){
		Point p = pop();
		for(int i = 0; i < 4; i++){
			int hang = p.row + dx[i];
			int cot = p.col + dy[i];
			if(hang >= 0 && hang < M && cot >= 0 && cot < N && visitBFS[hang][cot] == -1 && map[hang][cot] != 2){
				visitBFS[hang][cot] = visitBFS[p.row][p.col] + 1;
				Point tmp;
				tmp.row = hang;
				tmp.col = cot;
				push(tmp);
				if(map[hang][cot] == 1){
					int end = dust[hang][cot];
					matrix[start][end] = visitBFS[hang][cot];
					matrix[end][start] = visitBFS[hang][cot];
				}
			}
		}
	}
}

bool check(){
	int tmp;
	for(int i = 0; i < cntDust; i++){
		tmp = 0;
		for(int j = 0; j < cntDust; j++){
			tmp += matrix[i][j];
		}
		if(tmp == 0) return true;
	}
	return false;
}



void backTrack(int pos, int pre, int dem){
	if(pos >= cntDust){
		if(answer > dem)
			answer = dem;
		return;
	}
	if(dem > answer) return;
	for(int i = 1; i < cntDust; i++){
		if(visitBackTrack[i] == 0 ){
			visitBackTrack[i] = 1;
			backTrack(pos+1, i, dem + matrix[i][pre]);
			visitBackTrack[i] = 0;
		}
	}
}

int main(){
	//freopen("Text.txt", "r", stdin);
	int T; cin >> T;
	for(int tc = 1; tc <= T; tc++){
		cin >> M >> N;
		cntDust = 1;
		for(int i = 0; i < M; i++){
			for(int j = 0; j < N; j++){
				cin >> map[i][j];
				if(map[i][j] == 3){
					toaDo[0].row = i;
					toaDo[0].col = j;
				}
				dust[i][j] = -1;
				if(map[i][j] == 1){
					toaDo[cntDust].row = i;
					toaDo[cntDust].col = j;
					dust[i][j] = cntDust;
					cntDust++;
				}
			}
		}
		for(int i = 0; i < 30; i++){
			for(int j = 0; j< 30; j++){
				matrix[i][j] = 0;
			}
		}

		for(int i = 0; i < cntDust; i++){
			visitBackTrack[i] = 0;
			BFS(i);
		}
		answer = 1e5;
		backTrack(1, 0, 0);
		cout << "Case #" << tc << endl;
		if(check()) cout << "-1\n";
		else
			cout << answer << endl;
	}
	return 0;
}
