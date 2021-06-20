package com.study.training.baek;

import java.util.Scanner;

public class Q_2588 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        String bToString = String.valueOf(b);

        int size = bToString.length();
        int[] output = new int[size + 1];
        for (int i = 0; i < size; i++) {

            int c = bToString.charAt(size - 1 - i) - 48;
            output[i] = a * c;

        }
        output[size] = a * b;

        for (int anw : output) {
            System.out.println(anw);
        }
    }

}
