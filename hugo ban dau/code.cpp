#include <iostream>
using namespace std;
int map[51][51], visit[51][51];
int M, N, Hx, Hy, Power;
int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};
int dem;

int Type[8][4] = {
	{0, 0, 0, 0},
	{1, 1, 1, 1},
	{1, 1, 0, 0},
	{0, 0, 1, 1},
	{1, 0, 0, 1},
	{0, 1, 0, 1},
	{0, 1, 1, 0},
	{1, 0, 1, 0}
};

int q[1000000];
int front, rear;
void init(){
	front = 0;
	rear = 0;
}

void push(int a) {
	q[rear++] = a;
}

bool isEmpty(){
	return front == rear;
}

int pop(){
	return q[front++];
}



bool check(int h1, int c1, int h2, int c2){
	int type_1 = map[h1][c1];
	int type_2 = map[h2][c2];
	if(h1 == h2){
		if(c1 > c2){
			if(Type[type_1][2] == 0) return false;
			if(Type[type_2][3] == 0) return false;
		} else {
			if(Type[type_1][3] == 0) return false;
			if(Type[type_2][2] == 0) return false;
		}
	} else if (c1 == c2) {
		if(h1 > h2){
			if(Type[type_1][0] == 0) return false;
			if(Type[type_2][1] == 0) return false;
		} else {
			if(Type[type_1][1] == 0) return false;
			if(Type[type_2][0] == 0) return false;
		}
	}
	return true;
}

void BFS(){
	init();
	push(Hx);
	push(Hy);
	visit[Hx][Hy] = 1;
	while(!isEmpty()){
		int hang = pop();
		int cot = pop();
		for(int i = 0; i < 4; i++){
			int h = hang + dx[i];
			int c = cot + dy[i];
			if(h >= 0 && h < M && c >= 0 && c < N && visit[h][c] == 0){
				if(check(hang, cot, h, c) && visit[hang][cot] + 1 <= Power){
					visit[h][c] = visit[hang][cot] + 1;
					push(h);
					push(c);
					dem++;
				}
			}
		}
	}
}


int main(){
	freopen("Text.txt", "r", stdin);
	int T; cin >> T;
	for(int tc = 1; tc <= T; tc++){
		cin >> M >> N >> Hx >> Hy >> Power;
		for(int i = 0; i < M; i++){
			for(int j = 0; j < N; j++){
				cin >> map[i][j];
				visit[i][j] = 0;
			}
		}
		dem = 1;
		BFS();
		cout << "Case #" << tc << endl << dem << endl;
	}
	return 0;
}
