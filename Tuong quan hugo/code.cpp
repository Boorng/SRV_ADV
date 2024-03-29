#include <iostream>
using namespace std;
int nCong, answer;
struct Cong {
	int nBinhSi, chiPhi;
};
Cong arr[21];
int binhSi[4];
int visit[21];
void dich(){
	binhSi[2] = binhSi[1];
	binhSi[1] = binhSi[0];
	binhSi[0] = 0;
}
bool check(int nbinhsi){
	int tong = 0;
	for(int i = 0; i < 3; i++){
		tong += binhSi[i];
	}
	if(tong >= nbinhsi) return true;
	return false;
}

int test[10];
void backTrack(int vitri, int chiphi){
	if(vitri == nCong){
		/*for(int i = 0; i < nCong; i++){
			cout << test[i] << " ";
		}
		cout << chiphi << endl;*/
		if(chiphi < answer)
			answer = chiphi;
		return;
	}
	if(chiphi > answer) return;
	for(int i = 0; i < 3; i++){
			if(i == 0){	
				test[vitri] = i;
				int bk[4] = {0};
				for(int m = 0; m < 4; m++)
					bk[m] = binhSi[m];
				backTrack(vitri+1, chiphi + arr[vitri].chiPhi);
				for(int m = 0; m < 4; m++)
					binhSi[m] = bk[m];
				
			}
			if(i == 1){
				test[vitri] = i;
				int bk[4];
				for(int m = 0; m < 4; m++)
					bk[m] = binhSi[m];
				binhSi[0] += arr[vitri].nBinhSi;
				backTrack(vitri+1, chiphi + 2*arr[vitri].chiPhi);
				for(int m = 0; m < 4; m++)
					binhSi[m] = bk[m];
			
			}
			if(i == 2 && check(arr[vitri].nBinhSi)){
				test[vitri] = i;
				int bk[4];
				for(int m = 0; m < 4; m++)
					bk[m] = binhSi[m];
				int tmp = arr[vitri].nBinhSi;
				for(int k = 2; k >= 0; k--){
					if(binhSi[k] >= tmp){
						binhSi[k] -= tmp;
						tmp = 0;
						break;
					}
					if(binhSi[k] < tmp){
						tmp -= binhSi[k];
						binhSi[k] = 0;
					}
				}
				dich();
				backTrack(vitri+1, chiphi);
				for(int m = 0; m < 4; m++)
					binhSi[m] = bk[m];
			}
		}

}

int main(){
	freopen("Text.txt", "r", stdin);
	int T; cin >> T;
	for(int tc = 1; tc <= T; tc++){
		cin >> nCong;
		for(int i = 0; i < nCong; i++){
			visit[i] = 0;
			cin >> arr[i].nBinhSi >> arr[i].chiPhi;
		}
		for(int i = 0; i < 4; i++)
			binhSi[i] = 0;
		answer = 1e7;
		backTrack(0, 0);
		cout << "#" << tc << " " << answer << endl;
	}
	return 0;
}
