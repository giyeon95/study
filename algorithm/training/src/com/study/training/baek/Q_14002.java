package com.study.training.baek;

import java.util.ArrayList;
import java.util.Scanner;

public class Q_14002 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] input = new int[n];
        int[] d = new int[n];
        ArrayList[] dl = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            input[i] = sc.nextInt();
            dl[i] = new ArrayList<>();
        }

        d[0] = 1;
        dl[0].add(input[0]);

        for (int i = 1; i < n; i++) {
            d[i] = 1;

            for (int j = i - 1; j >= 0; j--) {
                if (input[i] > input[j] && d[j] >= d[i]) {
                    d[i] = d[j] + 1;
                    dl[i].clear();

                    dl[i].addAll(dl[j]);
                }
            }
            dl[i].add(input[i]);
        }

        int pos = 0;
        int max = -1;
        for (int i = 0; i < n; i++) {
            if (max < d[i]) {
                max = d[i];
                pos = i;
            }
        }
        System.out.println(max);

        for (int i = 0; i < dl[pos].size(); i++) {
            System.out.print(dl[pos].get(i) + " ");
        }

    }
}
