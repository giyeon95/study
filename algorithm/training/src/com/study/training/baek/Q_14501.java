package com.study.training.baek;

import java.util.Scanner;

public class Q_14501 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();

        int[] ti = new int[cnt + 1];
        int[] pi = new int[cnt + 1];
        int[] d = new int[cnt + 1];

        for (int i = 0; i < cnt; i++) {
            ti[i] = sc.nextInt();
            pi[i] = sc.nextInt();

        }

        for (int i = 2; i <= cnt; i++) {

            for (int j = 1; j < i; j++) {
                if (i - j >= ti[j]) {
                    d[i] = Math.max(pi[i] + d[j], d[i]);
                }
            }
        }

        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= cnt; i++) {
            if (i + ti[i] <= cnt + 1) {
                if (max < d[i]) {
                    max = d[i];
                }
            }
        }

        System.out.println(max);

//        int stp = ti[0];
//        int money = pi[0];

    }

}
