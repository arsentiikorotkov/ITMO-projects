#include <iostream>
#include <vector>

using namespace std;

int min (int a, int b) {
    return a > b ? b : a;
}

int main()
{
    int n;
    cin >> n;
    
    vector<vector<int>> w(n + 1, vector<int> (n + 1, 0));
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            int wei;
            cin >> wei;
            w[i][j] = wei;
        }
    }
    
    for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <=n; j++) {
                w[i][j] = min(w[i][j], w[i][k] + w[k][j]);
            }
        }
    }
    
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cout << w[i][j] << " ";
        }
        cout << endl;
    }
    
    return 0;
}