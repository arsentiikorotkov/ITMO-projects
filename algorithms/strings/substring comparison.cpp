#include <iostream>
#include <vector>

using namespace std;

long long x = 149;

//long long hah(vector<long long>& hash_pref, vector<long long>& pows, int l, int r) {
//    if (l == 0) {
//        return hash_pref[r];
//    }
//    return (hash_pref[r] - hash_pref[l - 1]) / pows[l];
//}

long long hah(vector<long long>& hash_pref, vector<long long>& pows, int l, int r) {
    if (l == 0) {
        return hash_pref[r];
    }
    return (hash_pref[r] - hash_pref[l - 1] * pows[r - l + 1]);
}

bool check(vector<long long>& hash_pref, vector<long long>& pows, int l1, int r1, int l2, int r2) {
    return (hah(hash_pref, pows, l1, r1) == hah(hash_pref, pows, l2, r2));
}

int main()
{
    string s;
    int m, n;
    cin >> s >> m;
    
    n = s.size();
    
    vector<long long> pows(n);
    pows[0] = 1;
    for (int i = 1; i < n; i++) {
        pows[i] = pows[i - 1] * x;
    }
    
    vector<long long> hash_pref(n);
    
    //hash_pref[0] = s[0];
    //for (int i = 1; i < n; i++) {
    //    hash_pref[i] = hash_pref[i - 1] + s[i] * pows[i];
    //}
    
    hash_pref[0] = s[0];
    for (int i = 1; i < n; i++) {
        hash_pref[i] = hash_pref[i - 1] * x + s[i];
    }
    
    for (int i = 0; i < m; i++) {
        int l1, r1, l2, r2;
        cin >> l1 >> r1 >> l2 >> r2;
        l1--; l2--; r1--; r2--;
        
        if (check(hash_pref, pows, l1, r1, l2, r2)) {
            cout << "Yes";
        } else {
            cout << "No";
        }
        cout << endl;
        
    }

    return 0;
}