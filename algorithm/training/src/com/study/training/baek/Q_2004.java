package com.study.training.baek;

import java.util.Scanner;

//참고: https://ksj14.tistory.com/entry/BackJoon1676-팩토리얼-0의-개수
public class Q_2004 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        long twoCount = getTwoCount(n) - getTwoCount(m) - getTwoCount(n - m);
        long fiveCount = getFiveCount(n) - getFiveCount(m) - getFiveCount(n - m);

        System.out.println(Math.min(twoCount, fiveCount));
    }

    private static long getTwoCount(long n) {
        long cnt = 0;
        for (long i = 2; i <= n; i *= 2) {
            cnt += n / i;
        }
        return cnt;
    }

    private static long getFiveCount(long n) {
        long cnt = 0;
        for (long i = 5; i <= n; i *= 5) {
            cnt += n / i;
        }
        return cnt;
    }
}
