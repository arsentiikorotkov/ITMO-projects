#include<iostream>
#include<vector>
#include<map>
#include<set>
 
using namespace std;

long long count;

void dfs1(vector<vector<long long>>& v, vector<long long>& t, long long start, long long mid, long long n) {
    count--;
    t[start] = 1;
    
    for (long long i = 1; i <= n; i++) {
        if (t[i] == -1 && v[start][i] <= mid) {
            dfs1(v, t, i, mid, n);
        }
    }
}

void dfs2(vector<vector<long long>>& v, vector<long long>& t, long long start, long long mid, long long n) {
    count--;
    t[start] = 1;
    
    for (long long i = 1; i <= n; i++) {
        if (t[i] == -1 && v[i][start] <= mid) {
            dfs2(v, t, i, mid, n);
        }
    }
}

bool check(vector<vector<long long>>& v, long long n, long long mid) {
    vector<long long> t1(n + 1, -1);
    count = n;
    dfs1(v, t1, 1, mid, n);
    if (count != 0) {
        return false;
    } else {
        vector<long long> t2(n + 1, -1);
        count = n;
        dfs2(v, t2, 1, mid, n);
        if (count != 0) {
            return false;
        } else {
            return true;
        }
    }
}
 
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int n;
    cin >> n;
    
    long long r = -1;
    vector<vector<long long>> v(n + 1, vector<long long> (n + 1, 0));
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            long long w;
            cin >> w;
            if (w > r) {
                r = w;
            }
            v[i][j] = w;
        }
    }
    
    long long l = -1;
    r = r + 1;
    long long mid;
    while (r - l > 1) {
        mid = (l + r) / 2;
        if (!check(v, n, mid)) {
            l = mid;
        } else {
            r = mid;
        }
    }
    
    cout << r;
    
    return 0;
}