package com.study.training.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Q_3055 {

    static int n;
    static int m;
    static int d[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");

        n = Integer.parseInt(NM[0]);
        m = Integer.parseInt(NM[1]);

        char[][] field = new char[n][m];
        d = new int[n][m];

        for (int y = 0; y < n; y++) {
            field[y] = br.readLine().toCharArray();
        }

        int[][] wft = new int[n][m];

        for (int y = 0; y < n; y++) {
            Arrays.fill(wft[y], Integer.MAX_VALUE);
            Arrays.fill(d[y], -1);
        }

        Queue<Item> q = new LinkedList<>();

        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == '*') {
                    q.add(new Item(y, x));
                    wft[y][x] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            Item item = q.remove();
            int y = item.y;
            int x = item.x;

            for (int i = 0; i < 4; i++) {
                int dy = y + pos[i][0];
                int dx = x + pos[i][1];

                if (inRange(dy, dx) && wft[dy][dx] == Integer.MAX_VALUE) {
                    if (field[dy][dx] == 'X' || field[dy][dx] == 'D' || field[dy][dx] == 'S') {
                    } else {
                        q.add(new Item(dy, dx));
                        wft[dy][dx] = wft[y][x] + 1;
                    }
                }
            }
        }

        int anw = bfs(field, wft);

        if (anw == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(anw);
        }

    }

    static int[][] pos = new int[][]{
        {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };

    static int bfs(char[][] field, int[][] water) {
        Queue<Item> q = new LinkedList<>();

        loop:
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (field[y][x] == 'S') {
                    q.add(new Item(y, x));
                    d[y][x] = 0;
                    break loop;
                }
            }
        }

        while (!q.isEmpty()) {
            Item item = q.remove();
            int y = item.y;
            int x = item.x;

            if (field[y][x] == 'D') {
                return d[y][x];
            }

            for (int i = 0; i < pos.length; i++) {
                int dy = item.y + pos[i][0];
                int dx = item.x + pos[i][1];

                if (inRange(dy, dx) && water[dy][dx] > d[y][x] + 1 && d[dy][dx] == -1 && field[dy][dx] != 'X') {
                    q.add(new Item(dy, dx));
                    d[dy][dx] = d[y][x] + 1;
                }
            }
        }

        return -1;
    }

    static class Item {

        int y;
        int x;

        private Item(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static boolean inRange(int y, int x) {

        if (y >= n || y < 0) {
            return false;
        }
        if (x >= m || x < 0) {
            return false;
        }

        return true;
    }

}
