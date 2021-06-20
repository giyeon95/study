package com.study.training.baek;

import java.util.Scanner;

public class Q_11053 {

    public static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        int[] a = new int[n];

        //input
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int anw = lis(a);

        System.out.println(anw);
    }

    public static int lis(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int ret = 1;

        for (int i = 0; i < n; i++) {
            int[] next = new int[n];
            int nextIndex = 0;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] < arr[j]) {
                    next[nextIndex++] = arr[j];
                    ret = Math.max(ret, 1 + lis(next));
                }
            }
        }
        return ret;
    }
}
