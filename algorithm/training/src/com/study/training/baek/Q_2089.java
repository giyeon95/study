package com.study.training.baek;

import java.util.Scanner;

public class Q_2089 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        if (n == 0) {
            System.out.println(0);
        } else {
            go(n);
        }
    }

    static void go(int n) {
        if (n == 1) {
            System.out.print(1);
            return;
        } else if (n == 0) {
            return;
        }
        if (n % -2 == -1) {
            n = (n / -2) + 1;
            go(n);
            System.out.print(1);
        } else {
            int mod = n % -2;
            n = (n / -2);
            go(n);
            System.out.print(mod);
        }
    }
}
