package com.study.training.baek;

import java.util.Scanner;

public class Q_1248 {

    static int n;
    static int[] anw;
    static int[][] a;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        anw = new int[n];
        String str = sc.next();
        a = new int[n][n];

        int pos = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                char c = str.charAt(pos++);
                if (c == '-') {
                    a[i][j] = -1;
                } else if (c == '+') {
                    a[i][j] = 1;
                } else if (c == '0') {
                    a[i][j] = 0;
                }
            }
        }

        if (go(0)) {
            for (int i = 0; i < n; i++) {
                System.out.print(anw[i] + " ");
            }
        }
    }

    static boolean checkIsAbleSum(int index) {
        for (int i = 0; i < index; i++) {
            int sum = 0;
            for (int j = i; j < index; j++) {
                sum += anw[j];
                if (a[i][j] < 0 && !(sum < 0)) {
                    return false;
                } else if (a[i][j] == 0 && !(sum == 0)) {
                    return false;
                } else if (a[i][j] > 0 && !(sum > 0)) {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean go(int index) {

        if (!checkIsAbleSum(index)) {
            return false;
        }

        if (index == n) {
            return true;
        }

        if (a[index][index] > 0) {
            for (int i = 1; i <= 10; i++) {
                anw[index] = i;
                if (go(index + 1)) {
                    return true;
                }
            }
        } else if (a[index][index] < 0) {
            for (int i = -1; i >= -10; i--) {
                anw[index] = i;
                if (go(index + 1)) {
                    return true;
                }
            }
        } else if (a[index][index] == 0) {
            anw[index] = 0;
            if (go(index + 1)) {
                return true;
            }
        }
        return false;
    }
}
