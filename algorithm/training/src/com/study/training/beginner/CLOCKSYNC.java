package com.study.training.beginner;

import java.util.Scanner;

public class CLOCKSYNC {


    public static int[][] switchButtons = new int[][]{
        {0, 1, 2},
        {3, 7, 9, 11},
        {4, 10, 14, 15},
        {0, 4, 5, 6, 7},
        {6, 7, 8, 10, 12},
        {0, 2, 14, 15},
        {3, 14, 15},
        {4, 5, 7, 14, 15},
        {1, 2, 3, 4, 5},
        {3, 4, 5, 9, 13}
    };

    static int CLOCK_SIZE = 16;
    static int SWITCH_SIZE = 10;
    static int INF = 9999;

    // 시계맞추기 난이도 중
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] clocks = new int[CLOCK_SIZE];

        int round = sc.nextInt();

        for (int i = 0; i < round; i++) {
            for (int k = 0; k < CLOCK_SIZE; k++) {
                clocks[k] = ((sc.nextInt() / 3) % 4); // 12: 0, 3: 1, 6: 2, 9: 3
            }

            for (int btn = 0; btn < SWITCH_SIZE; btn++) {
                int anw = solve(clocks, btn);
                if (anw == 0) {
                    System.out.println(anw);
                    break;
                } else if (anw >= INF) {
                    System.out.println(-1);
                    break;
                } else {
                    System.out.println(anw);
                    break;
                }

            }
        }

    }

    static int solve(int[] clocks, int buttonPos) {
        if (buttonPos == SWITCH_SIZE) {
            return areAligned(clocks) ? 0 : INF;
        }

        int ret = INF;
        for (int cnt = 0; cnt < 4; cnt++) {
            ret = Math.min(ret, cnt + solve(clocks, buttonPos + 1));
            push(clocks, buttonPos);
        }
        return ret;
    }

    static void push(int[] clocks, int buttonPos) {
        for (int i = 0; i < switchButtons[buttonPos].length; i++) {
            clocks[switchButtons[buttonPos][i]] = ((clocks[switchButtons[buttonPos][i]] + 1) % 4);
        }
    }

    static boolean areAligned(int[] clocks) {
        for (int i = 0; i < CLOCK_SIZE; i++) {
            if (clocks[i] != 0) {
                return false;
            }
        }
        return true;
    }

}
