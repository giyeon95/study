package com.study.training.baek;

import java.util.Scanner;

public class Q_13398 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] input = new int[n];
        int[] d = new int[n];
        int[] dr = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = sc.nextInt();
        }

        d[0] = input[0];

        for (int i = 1; i < n; i++) {
            d[i] = input[i];
            if (d[i - 1] + input[i] > d[i]) {
                d[i] = d[i - 1] + input[i];
            }
        }

        dr[n - 1] = input[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            dr[i] = input[i];
            if (dr[i + 1] + input[i] > dr[i]) {
                dr[i] = dr[i + 1] + input[i];
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (max < d[i]) {
                max = d[i];
            }
        }

        for (int i = 1; i < n - 1; i++) {
            if (max < d[i - 1] + dr[i + 1]) {
                max = d[i - 1] + dr[i + 1];
            }
        }

        System.out.println(max);
    }

}
