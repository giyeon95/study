package com.study.training.baek;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q_6588 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> arrayList = new ArrayList<>();

        int input;
        do {
            input = sc.nextInt();
            arrayList.add(input);

        } while (input != 0);

        arrayList.stream()
            .filter(num -> num != 0)
            .map(Q_6588::summary)
            .forEach(System.out::println);
    }

    static String summary(int num) {
        if (num % 2 != 0) {
            return "Goldbach's conjecture is wrong.";
        }

        int a;
        int b;

        for (int i = num - 1; i >= 1; i = i - 2) {
            b = i;
            if (isPrimeNumber(b)) {
                a = num - b;

                if (isPrimeNumber(a)) {
                    return num + " = " + a + " + " + b;
                }
            }
        }

        return "Goldbach's conjecture is wrong.";
    }

    static boolean isPrimeNumber(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
