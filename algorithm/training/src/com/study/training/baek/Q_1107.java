package com.study.training.baek;

import java.util.Scanner;

public class Q_1107 {

    static boolean[] broken = new boolean[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cnt = sc.nextInt();
        for (int i = 0; i < cnt; i++) {
            broken[sc.nextInt()] = true;
        }

        int ans = abs(n - 100);

        for (int i = 0; i <= 1000000; i++) {
            int c = i;
            int len = possible(c);
            if (len > 0) {
                int press = abs(c - n);
                if (ans > len + press) {
                    ans = len + press;
                }
            }
        }
        System.out.println(ans);
    }

    static int abs(int n) {
        return Math.abs(n);
    }

    static int possible(int c) {
        if (c == 0) {
            if (broken[0]) {
                return 0;
            } else {
                return 1;
            }
        }
        int len = 0;
        while (c > 0) {
            if (broken[c % 10]) {
                return 0;
            }
            len += 1;
            c /= 10;
        }
        return len;
    }
}