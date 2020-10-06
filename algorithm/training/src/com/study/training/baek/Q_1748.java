package com.study.training.baek;

import java.util.Scanner;

public class Q_1748 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long size = 10;
        int len = 1;
        int anw = 0;

        if (n < size) {
            anw = n;
        } else {
            anw += 9;
            len++;

            while (n >= size * 10) {
                long tmp = size;
                size *= 10;
                anw += (size - tmp) * len;

                len++;

            }
            anw += (n - size + 1) * len;
        }

        System.out.println(anw);

    }
}
