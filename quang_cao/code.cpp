#include <iostream>
using namespace std;
int so_khach;
int durations[4], point[4];
int time_dem[101], time_di[101];

int start_qc[4];
int end_qc[4];
int point_qc[4];
int visit[4], answer;


void tinh(int dem){
	int tong = 0;
	for(int i = 0; i < so_khach; i++){
		int tmp = 0;
		for(int j = 0; j < dem; j++){
			if(start_qc[j] >= time_dem[i] && end_qc[j] <= time_di[i]){
				if(point_qc[j] > tmp){
					tmp = point_qc[j];
				}
			}
		}
		tong = tong + tmp;
	}
	if(tong > answer)
		answer = tong;
}



void backtrack(int time, int dem){
	if(time > 50 || dem == 3){
		tinh(dem);
		return;
	}
	backtrack(time+1, dem);
	for(int i = 0; i < 3; i++){
		if(visit[i] == 0){
			visit[i] = 1;
			start_qc[dem] = time;
			end_qc[dem] = time+durations[i];
			point_qc[dem] = point[i];
			backtrack(time+durations[i], dem + 1);
			visit[i] = 0;
		}
	}
}


int main(){
	//freopen("Text.txt", "r", stdin);
	int T; cin >> T;
	for(int tc = 1; tc <= T; tc++){
		cin >> so_khach;
		for(int i = 0; i < 3; i++){
			cin >> durations[i];
			visit[i] = 0;
		}
		for(int i = 0; i < 3; i++){
			cin >> point[i];
		}
		for(int i = 0; i < so_khach; i++){
			cin >> time_dem[i];
			cin >> time_di[i];
			time_di[i] += time_dem[i];
		}
		answer = 0;
		backtrack(1, 0);
		cout << "Case #" << tc << endl << answer << endl;
	}
	return 0;
}
