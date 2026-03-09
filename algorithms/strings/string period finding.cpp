#include <iostream>
#include <vector>

using namespace std;

int main()
{
    string s;
    cin >> s;
    
    vector<int> p(s.size() + 1);
    p[0] = -1;
    
    for (int i = 1; i <= s.size(); i++) {
        int k = p[i - 1];
        while (k != -1 && s[k] != s[i - 1]) {
            k = p[k];
        }
        p[i] = k + 1;
    }
    
    if (p[s.size()] == p.size() - 1) {
        cout << 1;
    } else if (s.size() % (s.size() - p[s.size()]) == 0) {
        cout << s.size() - p[s.size()];
    } else {
        cout << s.size();
    }

    return 0;
}