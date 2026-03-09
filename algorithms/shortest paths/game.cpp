#include<iostream>
#include<vector>
#include<set>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int n, m, pos;
    cin >> n >> m >> pos;
    
    vector<vector<int>> out(n + 1);
    vector<int> counts(n + 1, 0);
    for (int i = 0; i < m; i++) {
        int x, y;
        cin >> x >> y;
        out[x].push_back(y);
        counts[y]++;
    }
    
    set<int> empty;
    for (int i = 1; i <= n; i++) {
        if (counts[i] == 0) {
            empty.insert(i);
        }
    }
    
    vector<int> result;
    while (!empty.empty()) {
        set<int> :: iterator it = empty.begin();
        result.push_back(*it);
        for (int i = 0; i < out[*it].size(); i++) {
            counts[out[*it][i]]--;
            if (counts[out[*it][i]] == 0) {
                empty.insert(out[*it][i]);
            }
        }
        empty.erase(it);
    }
    
    vector<bool> win(n + 1);
    for (int i = n - 1; i >= 0; i--) {
        int v = result[i];
        bool ind = true;
        for (int j = 0; j < out[v].size(); j++) {
            ind = ind && win[out[v][j]];
        }
        win[v] = !ind;
        if (v == pos) {
            if (win[v] == true) {
                cout << "First player wins";
            } else {
                cout << "Second player wins";
            }
            return 0;
        }
    }
    
    return 0;
}