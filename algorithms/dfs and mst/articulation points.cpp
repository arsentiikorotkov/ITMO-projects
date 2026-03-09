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

void dfs(vector<vector<int>>& v, vector<int>& t, vector<int>& up, set<int>& points, set<int>& all, int start, int tim, int parent) {
    t[start] = tim++;
    up[start] = t[start];
    int max = t[start] - 1, children = 0;
    all.erase(start);
    
    for (int i = 0; i < v[start].size(); i++) {
        if (v[start][i] == parent) {
            continue;
        } else if (t[v[start][i]] != -1) {
            up[start] = Min(up[start], t[v[start][i]]);
        } else {
            children++;
            dfs(v, t, up, points, all, v[start][i], tim, start);
            up[start] = Min(up[start], up[v[start][i]]);
            max = Max(max, up[v[start][i]]);
        }
    }

    if ((max >= t[start]) && (parent != 0)) {
        points.insert(start);
    } else if ((parent == 0) && (children > 1)) {
        points.insert(start);
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
    for (int i = 0; i < m; i++) {
        int x, y;
        cin >> x >> y;
        v[x].push_back(y);
        v[y].push_back(x);
    }
    
    vector<int> t(n + 1, -1);
    vector<int> up(n + 1, -1);
    set<int> points;
    set<int> all;
    for (int i = 1; i <= n; i++) {
        all.insert(i);
    }
    
    while (!all.empty()) {
        set<int> :: iterator it = all.begin();
        dfs(v, t, up, points, all, *it, 0, 0);
    }
    
    cout << points.size() << endl;
    set<int> :: iterator it = points.begin();
    for (int i = 0; it != points.end(); i++, it++) {
        cout << *it << " ";
    }
    
    return 0;
}