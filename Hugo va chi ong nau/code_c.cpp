#include <iostream>
using namespace std;
int M, N, map[25][25];
int visit[25][25];
int answer;
int dx[6] = {-2, 2, -1, -1, 1, 1};
int dy[6] = {0, 0, -1, 1, -1, 1};

void backTrack(int hang, int cot, int dem, int mat){
	if(dem == 4){
		if(mat >= answer)
			answer = mat;
		return;
	}
	for(int i = 0; i < 6; i++){
		int h = hang + dx[i];
		int c = cot + dy[i];
		if(h >= 1 && c >= 1 && h <= 2*M && c <= N && visit[h][c] == 0){
			visit[h][c] = 1;
			backTrack(h, c, dem + 1, mat + map[h][c]);
			backTrack(hang, cot, dem + 1, mat + map[h][c]);
			visit[h][c] = 0;
		}
	}
}

int main(){
	freopen("Text.txt", "r", stdin);
	int T; cin >> T;
	for(int tc = 1; tc <= T; tc++){
		cin >> N >> M;
		for(int i = 0; i < M; i++){
			for(int j = 1; j <= N; j++){
				if(j%2 == 0){
					cin >> map[i*2+2][j];
				} else
					cin >> map[i*2+1][j];

			}
		}
		for(int i = 0; i < 20; i++)
			for(int j = 0; j < 20; j++)
				visit[i][j] = 0;
		answer = 0;
		for(int i = 0; i < M; i++){
			for(int j = 1; j <= N; j++){
				if(j%2 == 0){
					visit[i*2+2][j] = 1;
					backTrack(i*2+2, j, 1, map[i*2+2][j]);
					visit[i*2+2][j] = 0;
				} else {
					visit[i*2+1][j] = 1;
					backTrack(i*2+1, j, 1, map[i*2+1][j]);
					visit[i*2+1][j] = 0;
				}
			}
		}
		cout << "Case #" << tc << endl << answer*answer << endl;
	}
	return 0;
}
///030200006938)
