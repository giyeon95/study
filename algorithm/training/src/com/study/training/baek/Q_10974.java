package com.study.training.baek;

import java.util.Scanner;

public class Q_10974 {

    static int[] input;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int in = sc.nextInt();

        int[] q = new int[in];
        boolean[] access = new boolean[in];
        input = new int[in];

        for (int i = 0; i < in; i++) {
            input[i] = i + 1;
        }

        permutation(q, access, 0, in);

    }

    static void permutation(int[] q, boolean[] access, int depth, int r) {
        if (depth == r) {
            for (int i = 0; i < r; i++) {
                System.out.print(q[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < input.length; i++) {
            if (!access[i]) {
                access[i] = true;
                q[depth] = input[i];

                permutation(q, access, depth + 1, r);

                access[i] = false;
            }
        }
    }

}
