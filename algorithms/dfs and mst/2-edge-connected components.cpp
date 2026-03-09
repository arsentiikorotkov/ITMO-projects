#include<iostream>
#include<vector>
#include<set>
#include<stack>
 
using namespace std;
 
int Max(int a, int b) {
    return a > b ? a : b;
}
 
int Min(int a, int b) {
    return a < b ? a : b;
}
 
void dfs(vector<vector<int>>& v, vector<int>& t, vector<int>& up, set<pair<int, int>>& bridges, set<int>& all, int start, 
int tim, int parent, stack<int>& peaks, vector<vector<int>>& components, vector<int>& nums) {
    peaks.push(start);
    t[start] = tim++;
    up[start] = t[start];
    all.erase(start);
    
    for (int i = 0; i < v[start].size(); i++) {
        if (v[start][i] == parent || v[start][i] == start) {
            continue;
        } else if (t[v[start][i]] != -1) {
            up[start] = Min(up[start], t[v[start][i]]);
        } else {
            dfs(v, t, up, bridges, all, v[start][i], tim, start, peaks, components, nums);
            up[start] = Min(up[start], up[v[start][i]]);
        }
    }
 
    if (up[start] == t[start] && parent != 0) {
        int count = 0;
        for (int i = 0; i < v[start].size(); i++) {
            if (v[start][i] == parent) {
                count++;
            }
        }
        if (count == 1) {
            pair<int, int> x;
            x.first = parent;
            x.second = start;
            bridges.insert(x);
            vector<int> temp;
            while (true) {
                temp.push_back(peaks.top());
                nums[peaks.top()] = components.size() + 1;
                peaks.pop();
                if (temp[temp.size() - 1] == start) {
                    break;
                }
            } 
            components.push_back(temp);
        }
    } 
    
    int self = 0;
    for (int i = 0; i < v[start].size(); i++) {
        if (v[start][i] == start) {
            self++;
        }
    }
    if (parent == 0 && (v[start].size() - self) == 0) {
        vector<int> temp;
        temp.push_back(peaks.top());
        nums[peaks.top()] = components.size() + 1;
        peaks.pop();
        components.push_back(temp);
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
    set<pair<int, int>> bridges;
    set<int> all;
    for (int i = 1; i <= n; i++) {
        all.insert(i);
    }
    
    stack<int> peaks;
    vector<vector<int>> components;
    vector<int> nums(n + 1, -1);
    while (!all.empty()) {
        set<int> :: iterator it = all.begin();
        dfs(v, t, up, bridges, all, *it, 0, 0, peaks, components, nums);
        if (!peaks.empty()) {
            vector<int> help;
            while (!peaks.empty()) {
                help.push_back(peaks.top());
                nums[peaks.top()] = components.size() + 1;
                peaks.pop();
            }
            components.push_back(help);
        }
    }
 
    cout << components.size() << endl;
    for (int i = 1; i <= n; i++) {
        cout << nums[i] << " ";
    }
    
    return 0;
}