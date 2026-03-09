#include <iostream>
#include <vector>

using namespace std;

int main()
{
    string h, t;
    cin >> h >> t;
    
    string s = h + "#" + t;
    
    vector<int> p(s.size() + 1);
    p[0] = -1;
    
    for (int i = 1; i <= s.size(); i++) {
        int k = p[i - 1];
        while (k != -1 && s[k] != s[i - 1]) {
            k = p[k];
        }
        p[i] = k + 1;
    }
    
    vector<int> res;
    
    for (int i = 1; i <= s.size(); i++) {
        if (p[i] == h.size()) {
            res.push_back(i - 2 * h.size());
        }
    }
    
    cout << res.size() << endl;
    for (int i = 0; i < res.size(); i++) {
        cout << res[i] << " ";
    }

    return 0;
}