#include<iostream>
#include<vector>
#include<set>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int n, m;
    cin >> n >> m;
    
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
    
    vector<int> grandi(n + 1, 0);
    for (int i = n - 1; i >= 0; i--) {
        int v = result[i];
        set<int> s;
        for (int j = 0; j < out[v].size(); j++) {
            s.insert(grandi[out[v][j]]);
        }
        if (s.empty()) {
            continue;
        } else {
            set<int> :: iterator it = s.begin();
            int last = -1;
            while (it != s.end()) {
                int now = *it;
                if (now == last + 1) {
                    last = now;
                    it++;
                    continue;
                } else {
                    break;
                }
            }
            grandi[v] = last + 1;
        }
    }
    
    for (int i = 1; i <= n; i++) {
        cout << grandi[i] << endl;
    }
    
    return 0;
}