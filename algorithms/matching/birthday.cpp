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

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int k;
    cin >> k;
    for (int dial_number = 0; dial_number < k; dial_number++) {
        //для одного набора

        int n, m;
        cin >> n >> m;

        vector<vector<bool>> matrix(n, (vector<bool> (m, false)));
        for (int i = 0; i < n; i++) {
            int temp = 1;
            cin >> temp;
            while (temp != 0) {
                matrix[i][temp - 1] = true;
                cin >> temp;
            }
        }

        //инвертируем ребра
        vector<vector<int>> edges(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!matrix[i][j]) {
                    edges[i].push_back(j + 1);
                }
            }
        }

        //теперь в этом найти максимальное независимое множество
        //для этого надо найти мин верш покрытие, его дополнение это максимальное независимое множество

        // cout << endl;
        // for (int i = 0; i < n; i++) {
        //     cout << i + 1 << ": ";
        //     for (int j = 0; j < edges[i].size(); j++) {
        //         cout << edges[i][j] << " ";
        //     }
        //     cout << endl;
        // }

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

        vector<int> boys;
        vector<int> girls;
        for (int i = 0; i < n; i++) {
            if (l_plus[i]) {
                boys.push_back(i + 1);
            }
        }
        for (int i = 0; i < m; i++) {
            if (!r_plus[i]) {
                girls.push_back(i + 1);
            }
        }

        cout << boys.size() + girls.size() << endl;
        cout << boys.size() << " " << girls.size() << endl;
        for (int boy : boys) {
            cout << boy << " ";
        }
        cout << endl;
        for (int girl : girls) {
            cout << girl << " ";
        }
        cout << endl;
    }

    return 0;
}