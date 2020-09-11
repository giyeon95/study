package com.study.training.baek;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q_4963 {

    static int weight;
    static int height;

    static Scanner sc;
    static boolean[][] check;
    static int[][] field;

    static List<Integer> isLandCnt = new ArrayList<>();

    //y.pos = 0 , x.pos = 1
    static int[][] pos = new int[][]{
        {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
    };


    public static void main(String[] args) {
        sc = new Scanner(System.in);

        do {
            weight = sc.nextInt();
            height = sc.nextInt();

            if(!(weight == 0 && height == 0)) {
                createQuestion(height, weight);

            }

        } while (!(weight == 0 && height == 0));

        isLandCnt.forEach(System.out::println);
    }

    static void createQuestion(int h, int w) {

        check = new boolean[h][w];
        field = new int[h][w];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                field[y][x] = sc.nextInt();
            }
        }

        int cnt = 0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (field[y][x] == 1 && check[y][x] == false) {
                    dfs(y, x, ++cnt);
                }
            }
        }

        isLandCnt.add(cnt);

    }

    static boolean isRange(int y, int x) {
        if (y < 0 || x < 0) {
            return false;
        }

        if (y >= height || x >= weight) {
            return false;
        }

        return true;
    }

    static void dfs(int y, int x, int cnt) {

        check[y][x] = true;

        for (int i = 0; i < 8; i++) {
            int dy = y + pos[i][0];
            int dx = x + pos[i][1];

            if (isRange(dy, dx)) {
                if (field[dy][dx] == 1 && check[dy][dx] == false) {
                    dfs(dy, dx, ++cnt);
                }
            }
        }
    }
}

