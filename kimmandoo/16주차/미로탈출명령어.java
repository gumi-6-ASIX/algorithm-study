import java.util.*;

class Solution {
    int[] di = { -1, 0, 1, 0 };
    int[] dj = { 0, 1, 0, -1 };
    int rr = 0;
    int cc = 0;
    int mm = 0;
    int nn = 0;
    TreeSet<String> ts = new TreeSet();

    public void dfs(int ci, int cj, String str, int k) {
        if (ci == rr && cj == cc && str.length() == k) {
            ts.add(str);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int ni = di[i] + ci;
            int nj = dj[i] + cj;
            if (ni < nn && ni >= 0 && nj < mm && nj >= 0) {
                if (i == 0)
                    dfs(ni, nj, str + "u", k);
                if (i == 1)
                    dfs(ni, nj, str + "r", k);
                if (i == 2)
                    dfs(ni, nj, str + "d", k);
                if (i == 3)
                    dfs(ni, nj, str + "l", k);
            }
        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        rr = r;
        cc = c;
        mm = m;
        nn = n;
        String answer = "";
        dfs(x, y, "", k);
        if (ts.size() == 0) {
            answer = "impossible";
        } else {
            answer = ts.first();
        }

        return answer;
    }
}