package com.study.training.baek;

import java.util.Scanner;

public class RQ_9095 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int round = sc.nextInt();

        int[] anws = new int[round];

        for (int t = 0; t < round; t++) {

            int n = sc.nextInt();
            int[] d = new int[n];

            d[0] = 1;
            if (n > 1) {
                d[1] = 2;
            }
            if (n > 2) {
                d[2] = 4;
            }

            for (int i = 3; i < n; i++) {
                d[i] = d[i - 1] + d[i - 2] + d[i - 3];
            }

            anws[t] = d[n -1];
        }

        for (int anw : anws) {
            System.out.println(anw);
        }
    }

}
