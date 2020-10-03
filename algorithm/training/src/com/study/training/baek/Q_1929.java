package com.study.training.baek;

import java.util.Scanner;

public class Q_1929 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = n; i <= m; i++) {
            if (isPrimeNumber(i)) {
                System.out.println(i);
            }
        }
    }

    static boolean isPrimeNumber(int n) {
        if(n ==1) {
            return false;
        }

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
