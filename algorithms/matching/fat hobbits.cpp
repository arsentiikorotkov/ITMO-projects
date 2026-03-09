#include<iostream>
#include<vector>
#include <fstream>

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
 
void dfs_lr(int v, vector<vector<int>> &edges, vector<int> &p, vector<bool> &l_plus, vector<bool> &r_plus, vector<bool> &mark) {
    if (mark[v - 1]) {
        return;
    }
    mark[v - 1] = true;
    l_plus[v - 1] = true;
 
    for (int i = 0; i < edges[v - 1].size(); i++) {
        int u = edges[v - 1][i];
        r_plus[u - 1] = true;
        dfs_lr(p[u - 1], edges, p, l_plus, r_plus, mark);
    }
 
}

void dfs_hobbits(int v, vector<vector<int>> &matrix, vector<vector<int>> &edges, vector<bool> &mark, int number) {
    if (mark[v - 1]) {
        return;
    }
    mark[v - 1] = true;
    
    //edges[number - 1].push_back(v);
    if (v != number) {
        edges[number - 1].push_back(v);
    }

    for (int i = 0; i < matrix[v - 1].size(); i++) {
        if (matrix[v - 1][i] == 1) {
            dfs_hobbits(i + 1, matrix, edges, mark, number);
        }
    }

    return;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    ifstream fin("hobbits.in");
    ofstream fout("hobbits.out");

    int N;
    fin >> N;

    vector<vector<int>> matrix(N, vector<int> (N, 0));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            int item;
            fin >> item;
            matrix[i][j] = item;
        }
    }
    
    vector<vector<int>> edges(N);
    for (int v = 0; v < N; v++) {
        vector<bool> mark(N, false);
        dfs_hobbits(v + 1, matrix, edges, mark, v + 1);
    }
    
    
    int n = N, m = N;
    //теперь в этом найти максимальное независимое множество
    //для этого надо найти мин верш покрытие, его дополнение это максимальное независимое множество
    //тут ищем макс псоч, так как он нужен для мин верш покр

    vector<int> p(m, -1);
    for (int v = 0; v < n; v++) {
        vector<bool> mark(n, false);
        dfs(v + 1, edges, p, mark);
    }

    //находим ненасыщенные вершины левой доли
    vector<bool> saturation(n, false);
    for (int i = 0; i < m; i ++) {
        if (p[i] != -1) {
            saturation[p[i] - 1] = true;
        }
    }

    //здесь должен быть дфс и разделение на множества вершин
    vector<bool> l_plus(n, false);
    vector<bool> r_plus(m, false);
    for (int v = 0; v < n; v++) {
        if (!saturation[v]) {
            vector<bool> mark(n, false);
            dfs_lr(v + 1, edges, p, l_plus, r_plus, mark);
        }
    }


    //получение результата
    
    vector<int> res;
        for (int i = 0; i < n; i++) {
        if (!(!l_plus[i] or r_plus[i])) {
            res.push_back(i + 1);
        }
    }
    fout << res.size() << endl;
    for (int i = 0; i < res.size(); i++) {
        fout << res[i] << " ";
    }
    
    
    fout.close();

    return 0;
}