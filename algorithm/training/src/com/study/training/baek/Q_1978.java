package com.study.training.baek;

import java.util.Scanner;

public class Q_1978 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] inputs = new int[n];
        int anw = 0;

        for (int i = 0; i < n; i++) {
            inputs[i] = sc.nextInt();
        }

        for (int input : inputs) {
            if (isPrimeNumber(input)) {
                anw++;
            }
        }
        System.out.println(anw);
    }

    static boolean isPrimeNumber(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
