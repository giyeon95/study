package com.study.training.baek;

import java.util.Scanner;

public class Q_11653 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        go(n);
    }

    private static void go(int n) {
        if (n == 1) {
            return;
        }

        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                System.out.println(i);
                go(n / i);
                break;
            }
        }
    }
}


