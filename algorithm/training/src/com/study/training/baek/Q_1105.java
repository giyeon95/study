package com.study.training.baek;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Q_1105 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] input = new int[n];
        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            input[i] = sc.nextInt();
        }

        d[0] = 1;
        for (int i = 1; i < n; i++) {
            d[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (input[i] > input[j] && d[i] <= d[j]) {
                    d[i] = d[j] + 1;
                }
            }
        }

        int max = Stream.of(d)
            .flatMapToInt(Arrays::stream)
            .boxed()
            .max(Integer::compareTo).orElse(0);

        System.out.println(max);
    }
}
