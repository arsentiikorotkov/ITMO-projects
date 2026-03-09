#include<iostream>
#include<vector>
#include<set>

using namespace std;

int Max(int a, int b) {
    return a > b ? a : b;
}

int Min(int a, int b) {
    return a < b ? a : b;
}

void dfs(vector<vector<int>>& v, vector<int>& t, vector<int>& up, set<pair<int, int>>& bridges, set<int>& all, int start, int tim, int parent) {
    t[start] = tim++;
    up[start] = t[start];
    all.erase(start);
    
    for (int i = 0; i < v[start].size(); i++) {
        if (v[start][i] == parent) {
            continue;
        } else if (t[v[start][i]] != -1) {
            up[start] = Min(up[start], t[v[start][i]]);
        } else {
            dfs(v, t, up, bridges, all, v[start][i], tim, start);
            up[start] = Min(up[start], up[v[start][i]]);
        }
    }

    if (up[start] > t[parent] && parent != 0) {
        pair<int, int> x;
        x.first = parent;
        x.second = start;
        bridges.insert(x);
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int n, m;
    cin >> n >> m;
    
    vector<vector<int>> v(n + 1);
    vector<pair<int, int>> numbers;
    for (int i = 0; i < m; i++) {
        int x, y;
        pair <int, int> in;
        cin >> x >> y;
        in.first = x;
        in.second = y;
        numbers.push_back(in);
        v[x].push_back(y);
        v[y].push_back(x);
    }
    
    vector<int> t(n + 1, -1);
    vector<int> up(n + 1, -1);
    set<pair<int, int>> bridges;
    set<int> all;
    for (int i = 1; i <= n; i++) {
        all.insert(i);
    }
    
    while (!all.empty()) {
        set<int> :: iterator it = all.begin();
        dfs(v, t, up, bridges, all, *it, 0, 0);
    }
    
    cout << bridges.size() << endl;
    for (int i = 0; i < numbers.size(); i++) {
        pair<int, int> inv;
        inv.first = numbers[i].second;
        inv.second = numbers[i].first;
        if (bridges.find(numbers[i]) != bridges.end() || bridges.find(inv) != bridges.end()) {
            cout << i + 1 << endl;
        }
    }
    
    return 0;
}