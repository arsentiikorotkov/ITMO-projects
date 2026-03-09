#include<iostream>
#include<vector>
#include<set>
#include<stack>
#include<map>

using namespace std;

int Max(int a, int b) {
    return a > b ? a : b;
}

int Min(int a, int b) {
    return a < b ? a : b;
}

void dfs(vector<vector<int>>& v, vector<int>& t, vector<int>& up, set<int>& points, set<int>& all, int start, int tim, 
int parent, stack<pair<int, int>>& edges, vector<vector<pair<int, int>>>& components, map<pair<int, int>, int>& nums) {
    t[start] = tim++;
    up[start] = t[start];
    int max = t[start] - 1, children = 0;
    all.erase(start);
    
    for (int i = 0; i < v[start].size(); i++) {
        if (v[start][i] == parent) {
            continue;
        } else if (t[v[start][i]] != -1) {
            up[start] = Min(up[start], t[v[start][i]]);
            if (t[v[start][i]] < t[start]) {
                pair<int, int> x;
                x.first = start;
                x.second = v[start][i];
                edges.push(x);
            }
        } else {
            pair<int, int> x;
            x.first = start;
            x.second = v[start][i];
            edges.push(x);
            children++;
            dfs(v, t, up, points, all, v[start][i], tim, start, edges, components, nums);
            if (up[v[start][i]] >= t[start]) {
                vector<pair<int, int>> temp;
                while (true) {
                    temp.push_back(edges.top());
                    nums[edges.top()] = components.size() + 1;
                    edges.pop();
                    if (temp[temp.size() - 1] == x) {
                        break;
                    }
                }
                components.push_back(temp);
            }
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
    vector<pair<int, int>> in;
    for (int i = 0; i < m; i++) {
        int x, y;
        pair<int, int> temp;
        cin >> x >> y;
        temp.first = x;
        temp.second = y;
        in.push_back(temp);
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
    
    stack<pair<int, int>> edges;
    vector<vector<pair<int, int>>> components;
    map<pair<int, int>, int> nums;
    while (!all.empty()) {
        set<int> :: iterator it = all.begin();
        dfs(v, t, up, points, all, *it, 0, 0, edges, components, nums);
        if (!edges.empty()) {
            vector<pair<int, int>> temp;
            while (!edges.empty()) {
                temp.push_back(edges.top());
                nums[edges.top()] = components.size() + 1;
                edges.pop();
            }
            components.push_back(temp);
        }
    }
    
    //cout << points.size() << endl;
    //set<int> :: iterator it = points.begin();
    //for (int i = 0; it != points.end(); i++, it++) {
    //    cout << *it << " ";
    //}
    
    //cout << endl << components.size() << endl;
    //for (int i = 0; i < components.size(); i++) {
    //    for (int j = 0; j < components[i].size(); j++) {
    //        int x, y;
    //        x = components[i][j].first;
    //        y = components[i][j].second;
    //        cout << x << " " << y << " | ";
    //    }
    //    cout << endl;
    //}
    
    //map<pair<int, int>, int> :: iterator iter = nums.begin();
    //for (int i = 0; iter != nums.end(); i++, iter++) {
    //    pair<int, int> x = iter->first; 
    //    cout << x.first << " " << x.second << " | " << iter->second << endl;
    //}
    
    cout << components.size() << endl;
    for (int i = 0; i < in.size(); i++) {
        map<pair<int, int>, int> :: iterator iterr;
        iterr = nums.find(in[i]);
        if (iterr != nums.end()) {
            cout << iterr->second << " ";
        } else {
            pair<int, int> inv;
            inv.first = in[i].second;
            inv.second = in[i].first;
            iterr = nums.find(inv);
            cout << iterr->second << " ";
        }
    }
    
    return 0;
}