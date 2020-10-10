package com.study.training.baek;

import java.util.Scanner;

public class Q_14889 {


    static int[][] a;
    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        combination(new boolean[n], 0, n, n / 2);

        System.out.println(MIN);
    }

    private static int getTeamStatus(int[] teams) {
        int status = 0;
        for (int i : teams) {
            for (int j : teams) {
                status += a[i][j];
            }
        }
        return status;
    }

    private static void combination(boolean[] check, int start, int n, int r) {
        if (r == 0) {
            int[] teamA = new int[n / 2];
            int[] teamB = new int[n / 2];
            int aPos = 0;
            int bPos = 0;

            for (int i = 0; i < n; i++) {
                if (check[i]) {
                    teamA[aPos++] = i;
                } else {
                    teamB[bPos++] = i;
                }
            }
            int abs = Math.abs(getTeamStatus(teamA) - getTeamStatus(teamB));
            if (MIN > abs) {
                MIN = abs;
            }

            return;
        }

        for (int i = start; i < n; i++) {
            check[i] = true;
            combination(check, i + 1, n, r - 1);
            check[i] = false;
        }
    }
}
