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
    
    if (empty.empty()) {
        cout << "-1";
        return 0;
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
    
    if (result.size() != n) {
        cout << "-1";
    } else {
        for (int i = 0; i < n; i++) {
            cout << result[i] << " ";
        }
    }
    
    return 0;
}