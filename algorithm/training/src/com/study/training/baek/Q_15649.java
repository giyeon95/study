package com.study.training.baek;

import java.util.Scanner;

public class Q_15649 {

    static int n; // maxNum
    static int m; // maxLength

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[m];
        check = new boolean[n];

        dfs(0);
    }

    static int[] arr;
    static boolean[] check;

    static void dfs(int cnt) {
        if (m == cnt) {
            for (int i = 0; i < m; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (check[i] == false) {
                check[i] = true;
                arr[cnt] = i + 1;
                dfs(cnt + 1);

                check[i] = false;
            }

        }
    }
}
