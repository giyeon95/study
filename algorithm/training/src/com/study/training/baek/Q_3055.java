package com.study.training.baek;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Scanner;

public class Q_3055 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        char[][] field = new char[n][m];

        for (int y = 0; y < n; y++) {
            String line = sc.next();
            for (int x = 0; x < m; x++) {
                field[y][x] = line.charAt(x);
            }
        }

        int[][] fillWaterTime = fillWaterTime(field);

        String anw = dfs(field, fillWaterTime)
            .map(Object::toString)
            .orElse("KAKTUS");

        System.out.println(anw);

    }

    static int[][] pos = new int[][]{
        {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };

    static Optional<Integer> dfs(char[][] field, int[][] water) {
        Queue<Item> q = new LinkedList<>();
        boolean[][] check = new boolean[field.length][field[0].length];

        loop:
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == 'S') {
                    q.add(new Item(y, x, 0));
                    check[y][x] = true;
                    break loop;
                }
            }
        }

        while (!q.isEmpty()) {
            Item item = q.remove();

            if (field[item.y][item.x] == 'D') {
                return Optional.of(item.t);
            }

            for (int i = 0; i < pos.length; i++) {
                int dy = item.y + pos[i][0];
                int dx = item.x + pos[i][1];
                int dt = item.t;

                if (inRange(field, dy, dx) && water[dy][dx] > dt + 1 && check[dy][dx] == false && field[dy][dx] != 'X') {
                    q.add(new Item(dy, dx, dt + 1));
                    check[dy][dx] = true;
                }
            }
        }

        return Optional.empty();
    }

    static int[][] fillWaterTime(char[][] field) {
        int[][] wft = new int[field.length][field[0].length];

        for (int y = 0; y < wft.length; y++) {
            Arrays.fill(wft[y], Integer.MAX_VALUE);
        }

        Queue<Item> q = new LinkedList<>();

        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == '*') {
                    q.add(new Item(y, x, 0));
                    wft[y][x] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            Item item = q.remove();

            for (int i = 0; i < 4; i++) {
                int dy = item.y + pos[i][0];
                int dx = item.x + pos[i][1];
                int dt = item.t;

                if (inRangeWater(field, dy, dx) && wft[dy][dx] == Integer.MAX_VALUE) {
                    q.add(new Item(dy, dx, dt + 1));
                    wft[dy][dx] = dt + 1;
                }
            }
        }
        return wft;
    }

    static class Item {

        int y;
        int x;
        int t;

        private Item(int y, int x, int t) {
            this.y = y;
            this.x = x;
            this.t = t;
        }
    }

    static boolean inRange(char[][] field, int y, int x) {
        int maxY = field.length;
        int maxX = field[0].length;

        if (y >= maxY || y < 0) {
            return false;
        }
        if (x >= maxX || x < 0) {
            return false;
        }

        return true;
    }


    static boolean inRangeWater(char[][] field, int y, int x) {
        if (!inRange(field, y, x)) {
            return false;
        }

        if (field[y][x] == 'X' || field[y][x] == 'D' || field[y][x] == 'S') {
            return false;
        }

        return true;
    }


}
