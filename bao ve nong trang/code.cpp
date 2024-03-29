#include <iostream>
using namespace std;
#define D 700
int M, N, dem, map[D][D], visit[D][D];
int dx[8] = {-1, -1, -1, 0, 0, 1, 1, 1};
int dy[8] = {-1, 0, 1, -1, 1, -1, 0, 1};
int front, rear;
int q[1000000];
void init(){
	front = 0;
	rear = 0;
}
void push(int a){
	q[rear] = a;
	rear++;
	rear %= 1000000;
}
int pop(){
	int a = q[front];
	front++;
	front %= 1000000;
	return a;
}
bool isEmpty(){
	return front == rear;
}

void BFS(int x, int y){
	init();
	visit[x][y] = 1;
	push(x);
	push(y);
	int tmp = 1;
	while(!isEmpty()){
		int hang = pop();
		int cot = pop();
		for(int i = 0; i < 8; i++){
			int h = hang + dx[i];
			int c = cot + dy[i];
			if(h >= 0 && h < M && c >= 0 && c < N){
				if(visit[h][c] == 0 && map[h][c] == map[hang][cot]){
					visit[h][c] = 1;
					push(h);
					push(c);
				} else {
					if(map[h][c] > map[hang][cot]){
						tmp = 0;
					}
				}
			}
		}
	}
	dem = dem + tmp;
}

int main(){
	freopen("Text.txt", "r", stdin);
	int T; cin >> T;
	for(int tc = 1; tc <= T; tc++){
		cin >> M >> N;
		for(int i = 0; i < D; i++)
			for(int j = 0; j < D; j++)
				visit[i][j] = 0;
		for(int i = 0; i < M; i++)
			for(int j = 0; j < N; j++)
				cin >> map[i][j];
		dem = 0;
		for(int i = 0; i < M; i++){
			for(int j = 0; j < N; j++){
				if(visit[i][j] == 0){
					BFS(i, j);
				}
			}
		}
		cout << "Case #" << tc << endl << dem << endl;
	}
	return 0;
}
