#includeiostream
#includevector
#includemap
 
using namespace std;

int fd(vectorint& lids, int x) {
    if (lids[x] == x) {
        return lids[x];
    } else {
        int fx = fd(lids, lids[x]);
        lids[x] = fx;
        return fx;
    }
}

void un(vectorint& lids, int x, int y) {
    int fx = fd(lids, x);
    int fy = fd(lids, y);
    if (fx  fy) {
        lids[y] = fx;
        lids[fy] = fx;
    } else if (fx  fy) {
        lids[x] = fy;
        lids[fx] = fy;
    }
}

bool same(vectorint& lids, int x, int y) {
    if (fd(lids, x) != fd(lids, y)) {
        return false;
    }
    return true;
}
 
int main()
{
    ios_basesync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int n, m;
    cin  n  m;
    
    vectorint lids(n + 1, 0);
    for (int i = 1; i = n; i++) {
        lids[i] = i;
    }
    
    multimapint, pairint, int edges;
    for (int i = 0; i  m; i++) {
        int x, y, w;
        cin  x  y  w;
        if (x != y) {
            edges.insert(make_pair(w, make_pair(x, y)));
        }
    }
    
    long long sum = 0;
    multimapint, pairint, int  iterator it = edges.begin();
    while (it != edges.end()) {
        int w = it-first, x = it-second.first, y = it-second.second;
        if (!same(lids, x, y)) {
            sum += w;
            un(lids, x, y);
        }
        it++;
    }
    
    cout  sum;
    
    return 0;
}