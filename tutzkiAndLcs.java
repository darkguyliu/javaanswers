    public static int tutzkiAndLcs(String a, String b) {
    // Write your code here
        int n = a.length();
        int m = b.length();

        int[][] pref = new int[n + 1][m + 1];
        int[][] suff = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    pref[i + 1][j + 1] = pref[i][j] + 1;
                } else {
                    pref[i + 1][j + 1] = Math.max(pref[i + 1][j],
                            pref[i][j + 1]);
                }
            }

        for (int i = n - 1; i >= 0; i--)
            for (int j = m - 1; j >= 0; j--) {
                if (a.charAt(i) == b.charAt(j)) {
                    suff[i][j] = suff[i + 1][j + 1] + 1;
                } else {
                    suff[i][j] = Math.max(suff[i][j + 1], suff[i + 1][j]);
                }
            }
        
        int cur = pref[n][m];
        
        int ret = 0;

        for (int i = 0; i <= n; i++) {
            boolean[] used = new boolean[256];
            for (int j = 0; j < m; j++) {
                if (used[b.charAt(j)]) {
                    continue;
                }
                
                int now = pref[i][j] + suff[i][j + 1] + 1;
                if (now == cur + 1) {
                    used[b.charAt(j)] = true;
                    ret++;
                }
            }
        }
    

        return ret;    

    }
