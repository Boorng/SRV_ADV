#include <iostream>
using namespace std;
int N, K, answer;
int arr[101];

void bat(int k){
	for(int i = 0; k+i*(k+1) <= N; i++){
		int tmp = arr[k+i*(k+1)];
		if(tmp == 0)
			arr[k+i*(k+1)] = 1;
		else 
			arr[k+i*(k+1)] = 0;
	}
}

int tong(){
	int tmp = 0;
	for(int i = 1; i <= N; i++){
		tmp += arr[i]; 
	}
	return tmp;
}

int tws[11] = {0};

void back_track(int vtPtu, int k, int npv){
	if(k == 1 || k == 2){
		if(answer > npv){
			answer = npv;
		}
	}
	if(k>=3){
		if(answer > npv){
			answer = npv;
		}
		return;
	}
	for(int i = vtPtu; i <= K; i++){
		bat(i);
		tws[i] = 1;
		back_track(i+1, k+1, tong());
		tws[i] = 0;
		bat(i);
	}
}

int main(){
	freopen("Text.txt", "r", stdin);
	int T; cin >> T;
	for(int tc = 1; tc <= T; tc++){
		cin >> N >> K;
		for(int i = 1; i <= N; i++){
			cin >> arr[i];
		}
		answer = tong();
		back_track(1, 0, tong());
		cout << "#" << tc << " " << N - answer << endl;
	}

	return 0;
}
