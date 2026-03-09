#include<iostream>
#include<vector>
#include<set>
#include<cmath>
#include <iomanip>

using namespace std;

double dist(vector<pair<int, int>>& coor, int i, int j) {
    return sqrt(pow(coor[i].first - coor[j].first, 2) + pow(coor[i].second - coor[j].second, 2));
}

int main()
{
    //ускорение
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    //ввод и составление массива координат и весов
    int n;
    cin >> n;
 
    vector<pair<int, int>> coor(n + 1);
    vector<pair<double, int>> minimum(n + 1, make_pair(40000, -1));
    for (int i = 1; i <= n; i++) {
        int x, y;
        cin >> x >> y;
        coor[i] = make_pair(x, y);
    }
    
    for (int i = 2; i <= n; i++) {
        minimum[i] = make_pair(dist(coor, i, 1), 1);
    }
    
    //оставшиеся вершины
    set<int> all;
    for (int i = 2; i <= n; i++) {
        all.insert(i);
    }
    
    //сам алгоритм
    double sum = 0;
    while (!all.empty()) {
        set<int> :: iterator iter = all.begin();
        double min = 40000;
        int peak = -1;
        for (int i = 0; iter != all.end(); iter++) {
            if (minimum[*iter].first < min) {
                min = minimum[*iter].first;
                peak = *iter;
            }
        }
        
        all.erase(peak);
        sum += min;
        
        iter = all.begin();
        for (int i = 0; iter != all.end(); iter++) {
            if (minimum[*iter].first > dist(coor, peak, *iter)) {
                minimum[*iter] = make_pair(dist(coor, peak, *iter), peak);
            }
        }
        
    } 
    
    cout << fixed << setprecision(20) << sum;
    
    return 0;
}