package com.study.training.baek;

import java.util.Scanner;

public class Q_1676 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int two = 0;
        int five = 0;

        for (int i = 2; i <= n; i++) {
            int sub = i;
            while (sub % 2 == 0) {
                ++two;
                sub /= 2;
            }

            while (sub % 5 == 0) {
                ++five;
                sub /= 5;
            }
        }

        if (two > five) {
            System.out.println(five);
        } else {
            System.out.println(two);
        }
    }
}
