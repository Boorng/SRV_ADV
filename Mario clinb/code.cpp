#include <iostream>
using namespace std;
int M, N, map[55][55], visit[55][55];
int H, sX, sY, eX, eY;
int answer;
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};
void rs(){
	for(int i = 0; i <= M; i++)
		for(int j = 0; j <= N; j++)
			visit[i][j] = 0;
}

int q[10000];
int front, rear;
void init(){
	front = 0;
	rear = 0;
}

void push(int a){
	q[rear++] = a;
}

bool isEmpty(){
	return front == rear;
}

int pop(){
	return q[front++];
}



void BFS(){
	init();
	rs();
	push(sX);
	push(sY);
	visit[sX][sY] = 1;
	while(!isEmpty()){
		int hang = pop();
		int cot = pop();
		for(int i = 0; i < 4; i++){
			for(int step = 1; step <= H; step++){
				int h = hang + step*dx[i];
				int c = cot + dy[i];
				if(h >= 0 && h < M && c >= 0 && c < N && map[h][c] == 1 && visit[h][c] == 0){
					visit[h][c] = visit[hang][cot] + 1;
					push(h);
					push(c);
				}
				if(map[h][c] == 3){
					answer = H;
				}
			}
		}
	}
}




int main(){
	freopen("Text.txt", "r", stdin);
	int T; cin >> T;
	for(int tc = 1; tc <= T; tc++){
		cin >> M >> N;
		for(int i = 0; i < M; i++){
			for(int j = 0; j < N; j++){
				cin >> map[i][j];
				if(map[i][j] == 2){
					sX = i;
					sY = j;
				}
				if(map[i][j] == 3){
					eX = i;
					eY = j;
				}
			}
		}
		answer = -1;
		H = 1;
		while(answer == -1){
			BFS();
			H++;
		}
		cout << "Case #" << tc << endl << answer << endl;
	}
	return 0;
}
