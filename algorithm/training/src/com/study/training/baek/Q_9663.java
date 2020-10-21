package com.study.training.baek;

import java.util.Scanner;

public class Q_9663 {

    static int n;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int[] col = new int[n];

            col[0] = i;
            dfs(col, 0);
        }

        System.out.println(count);
    }

    private static void dfs(int[] col, int row) {
        if (row == n - 1) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            col[row + 1] = i;
            if (isPossaible(col, row + 1)) {
                dfs(col, row + 1);
            }
        }
    }

    private static boolean isPossaible(int[] col, int row) {
        for (int i = 0; i < row; i++) {
            if (col[i] == col[row]) {
                return false;
            }

            if (Math.abs(i - row) == Math.abs(col[i] - col[row])) {
                return false;
            }
        }
        return true;
    }
}