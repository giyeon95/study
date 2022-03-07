package com.study.training.baek;

import java.util.Scanner;

public class Q_2048 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int inputA = sc.nextInt();
        int inputB = sc.nextInt();
        int inputC = sc.nextInt();

        if (inputA == inputB && inputA == inputC) {
            System.out.println(10000 + (inputA * 1000));
        } else if (inputA == inputB) {
            System.out.println(1000 + (inputA * 100));
        } else if (inputA == inputC) {
            System.out.println(1000 + (inputA * 100));
        } else if (inputB == inputC) {
            System.out.println(1000 + (inputB * 100));
        } else {
            int max = Math.max(inputA, inputB);
            max = Math.max(max, inputC);

            System.out.println(max * 100);
        }
    }
}
