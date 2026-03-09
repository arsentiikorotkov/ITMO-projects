#include<iostream>
#include<vector>

using namespace std;


bool dfs(int v, vector<vector<int>> &edges, vector<int> &p, vector<bool> &mark) {
    if (mark[v - 1]) {
        return false;
    }
    mark[v - 1] = true;

    for (int i = 0; i < edges[v - 1].size(); i++) {
        int u = edges[v - 1][i];
        if (p[u - 1] == -1) {
            p[u - 1] = v;
            return true;
        } else {
            if (dfs(p[u - 1], edges, p, mark)) {
                p[u - 1] = v;
                return true;
            }
        }
    }

    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<int> p(m, -1);
    vector<vector<int>> edges(n);
    for (int i = 0; i < n; i++) {
        int temp = 1;
        cin >> temp;
        while (temp != 0) {
            edges[i].push_back(temp);
            cin >> temp;
        }
    }

    for (int v = 0; v < n; v++) {
        vector<bool> mark(n, false);
        dfs(v + 1, edges, p, mark);
    }

    vector<pair<int, int>> matching;
    for (int i = 0; i < m; i++) {
        if (p[i] != -1) {
            pair<int, int> edge;
            edge.first = p[i];
            edge.second = i + 1;
            matching.push_back(edge);
        }
    }

    cout << matching.size() << endl;
    for (auto & i : matching) {
        cout << i.first << " " << i.second << endl;
    }

    return 0;
}