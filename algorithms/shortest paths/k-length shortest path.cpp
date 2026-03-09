#include <iostream>
#include <vector>

using namespace std;

int min(int a, int b) {
    return a < b ? a : b;
}

int main()
{
    int n, m, l, s, max = 100000000;
    cin >> n >> m >> l >> s;
    
    vector<vector<pair<int, int>>> in(n + 1);
    for (int i = 0; i < m; i++) {
        int a, b, w;
        cin >> a >> b >> w;
        in[b].push_back(make_pair(a, w));
    }
    
    vector<vector<int>> d(n + 1, vector<int>(l + 1, max));
    d[s][0] = 0;
    for (int k = 1; k <= l; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < in[i].size(); j++) {
                d[i][k] = min(d[i][k], d[in[i][j].first][k - 1] + in[i][j].second);
            }
        }
    }
    
    for (int i = 1; i <= n; i++) {
        if (d[i][l] > 10000000) {
            cout << "-1";
        } else {
            cout << d[i][l];
        }
        cout << endl;
    }

    return 0;
}