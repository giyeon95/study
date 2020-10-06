package com.study.training.baek;

import java.util.Scanner;

public class Q_6064 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cnt = sc.nextInt();

        for (int i = 0; i < cnt; i++) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();

            System.out.println(solution(m, n, x, y));
        }
    }

    private static int solution(int m, int n, int x, int y) {
        int anw = 0;

        int init = -1;

        anw += x;
//        int dx = anw;
        int dy = anw;
        dy = reduce(n, dy);

        if (dy == y) {
            return anw;
        }

        init = dy;

        do {
            anw += m;
            dy = reduce(n, dy + m);
            if (dy == y) {
                return anw;
            }

            if (dy == init) {
                return -1;
            }

        } while (!(dy == init || dy == y));

        return -2;
    }

    private static int reduce(int n, int y) {
        int tmp = y;
        while (n < tmp) {
            tmp -= n;
        }
        return tmp;
    }
}
