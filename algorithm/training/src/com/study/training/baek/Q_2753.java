package com.study.training.baek;

import java.util.Scanner;

public class Q_2753 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        if (isLeapYear(input)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static boolean isLeapYear(int input) {
        return input % 4 == 0 && (input % 100 != 0 || input % 400 == 0);
    }

}
