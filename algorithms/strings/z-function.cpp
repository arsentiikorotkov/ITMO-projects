#include <iostream>
#include <vector>

using namespace std;

int Min(int a, int b) {
    return a < b ? a : b;
}

int Max(int a, int b) {
    return a > b ? a : b;
}

int main()
{
    string s;
    cin >> s;
    
    vector<int> z(s.size() + 1);
    
    int l;
    for (int i = 1; i <= s.size(); i++) {
        if (i > 1) {
            z[i] = Min(z[i - l], l + z[l] - i);
            z[i] = Max(z[i], 0);
        }
        
        while (s[i + z[i]] == s[z[i]]) {
            z[i]++;
        }
        
        if ((i == 1) || (i + z[i] > l + z[l])) {
            l = i;
        }
    }
    
    for (int i = 1; i <= s.size() - 1; i++) {
        cout << z[i] << " ";
    }

    return 0;
}