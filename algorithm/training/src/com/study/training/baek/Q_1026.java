package com.study.training.baek;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Q_1026 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        Arrays.sort(a);

        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }

        Integer[] b2 = Arrays.stream(b).boxed().toArray(Integer[]::new);
        Arrays.sort(b2, Collections.reverseOrder());

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += b2[i] * a[i];
        }
        System.out.println(sum);
    }
}
