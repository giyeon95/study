package com.study.training.baek;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q_14226 {

    final static int MAX = 1001;
    static int s;
    static int[][] d = new int[MAX][MAX];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        s = sc.nextInt();

        for (int i = 0; i < MAX; i++) {
            Arrays.fill(d[i], -1);
        }

        System.out.println(dfs(s));

    }

    static int dfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(0);
        d[1][0] = 0;

        while (!q.isEmpty()) {
            int s = q.remove();
            int c = q.remove();

            if (d[s][s] == -1) {
                d[s][s] = d[s][c] + 1;
                q.add(s);
                q.add(s);
            }

            if (inRange(s + c) && d[s + c][c] == -1) {
                d[s + c][c] = d[s][c] + 1;
                q.add(s + c);
                q.add(c);

            }

            if (inRange(s - 1) && d[s - 1][c] == -1) {
                d[s - 1][c] = d[s][c] + 1;
                q.add(s - 1);
                q.add(c);

            }
        }

        int ans = -1;
        for (int i = 0; i <= n; i++) {
            if (d[n][i] != -1) {
                if (ans == -1 || ans > d[n][i]) {
                    ans = d[n][i];
                }
            }
        }

        return ans;
    }


    static boolean inRange(int x) {
        if (x >= MAX) {
            return false;
        }

        if (x < 0) {
            return false;
        }

        return true;
    }
}
