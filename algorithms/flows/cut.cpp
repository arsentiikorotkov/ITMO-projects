#include<iostream>
#include<vector>
#include<queue>
#include<cmath>
#include<climits>
#include<set>
#include<algorithm>

using namespace std;

const int INF = INT_MAX;

class Edge {
public:
    int from;
    int to;
    int f;
    int c;
    int id;
    int index_back;

    Edge(int from_arg, int to_arg, int f_arg, int c_arg, int id_arg, int index_back_arg) {
        from = from_arg;
        to = to_arg;
        f = f_arg;
        c = c_arg;
        id = id_arg;
        index_back = index_back_arg;
    }
};

class Data {
public:
    int from;
    int to;
    int id;

    Data(int from_arg, int to_arg, int id_arg) {
        from = from_arg;
        to = to_arg;
        id = id_arg;
    }
};

void fill_inf(vector<int> &d) {
    for (int i = 0; i < d.size(); i++) {
        d[i] = INF;
    }
}

bool contains(set<int> &s, int x) {
    return s.find(x) != s.end();
}

bool bfs(vector<vector<Edge>> &edges, vector<int> &d, int n) {
    fill_inf(d);
    d[0] = 0;
    queue<int> queue;
    queue.push(1);
    
    while (!queue.empty()) {
        int v = queue.front();
        queue.pop();
        
        for (int i = 0; i < edges[v - 1].size(); i++) {
            int u = edges[v - 1][i].to;
            int c_delta = edges[v - 1][i].c - edges[v - 1][i].f;
            
            if ((c_delta > 0) and (d[u - 1] == INF)) {
                d[u - 1] = d[v - 1] + 1;
                queue.push(u);
            }
        }
    }
    
    return d[n - 1] != INF;
}

int dfs_dinic(int v, int min_delta, vector<vector<Edge>> &edges, vector<int> &d, vector<int> &p, int n) {
    if ((v == n) or (min_delta == 0)) {
        return min_delta;
    }
        
    for (int i = p[v - 1]; i < edges[v - 1].size(); i++) {
        int u = edges[v - 1][i].to;
        int c_delta = edges[v - 1][i].c - edges[v - 1][i].f;
        
        if (d[u - 1] == d[v - 1] + 1) {
            int delta = dfs_dinic(u, min(min_delta, c_delta), edges, d, p, n);
            
            if (delta != 0) {
                edges[v - 1][i].f += delta;
                edges[u - 1][edges[v - 1][i].index_back].f -= delta;
                
                return delta;
            }
        } 
        
        p[v - 1]++;
    }

    return 0;
}

int dfs_ff(int v, int min_delta, vector<vector<Edge>> &edges, vector<bool> &mark, int n) {
    if (v == n) {
        return min_delta;
    }
 
    if (mark[v - 1]) {
        return 0;
    }
    mark[v - 1] = true;
 
    for (int i = 0; i < edges[v - 1].size(); i++) {
        int u = edges[v - 1][i].to;
        int c_delta = edges[v - 1][i].c - edges[v - 1][i].f;
        if (c_delta > 0) {
            int delta = dfs_ff(u, min(min_delta, c_delta), edges, mark, n);
            if (delta > 0) {
                edges[v - 1][i].f += delta;
                edges[u - 1][edges[v - 1][i].index_back].f -= delta;
 
                return delta;
            }
        }
    }
 
    return 0;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
 
    int n, m;
    cin >> n >> m;
 
    vector<vector<Edge>> edges(n);
    vector<Data> data;
    for (int i = 0; i < m; i++) {
        int v, u, c;
        cin >> v >> u >> c;
 
        Edge e_vu(v, u, 0, c, i, edges[u - 1].size());
        Edge e_uv(u, v, 0, c, i, edges[v - 1].size());
 
        edges[v - 1].push_back(e_vu);
        edges[u - 1].push_back(e_uv);
 
        data.emplace_back(v, u, i);
    }
    
    
    int res = 0;
    vector<int> d(n, INF);
    while (bfs(edges, d, n)) {
        vector<int> p(n, 0);
        int delta = dfs_dinic(1, INF, edges, d, p, n);
        while (delta != 0) {
            res += delta;
            delta = dfs_dinic(1, INF, edges, d, p, n);
        }
    }

    
    //теперь надо найти ребра из одной компоненты в другую; ребра из вершин из true в false - нужные
    vector<bool> mark(n, false);
    dfs_ff(1, INF, edges, mark, n);
    set<int> left;
    set<int> right;
    for (int i = 0; i < n; i++) {
        if (mark[i]) {
            left.insert(i + 1);
        } else {
            right.insert(i + 1);
        }
    }
    
    
    vector<int> cut;
    for (int i = 0; i < data.size(); i++) {
        int v = data[i].from, u = data[i].to, id = data[i].id;
        if ((contains(left, v) and contains(right, u)) or ((contains(left, u) and contains(right, v)))) {
            cut.emplace_back(id + 1);
        }
    }
    sort(cut.begin(), cut.end());
    

    cout << cut.size() << " " << res << endl;
    for (int i = 0; i < cut.size(); i++) {
        cout << cut[i] << " ";
    }
 
    return 0;
}