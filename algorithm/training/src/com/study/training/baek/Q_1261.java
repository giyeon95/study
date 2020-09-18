package com.study.training.baek;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Q_1261 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] field = new int[n][m];
        boolean[][] check = new boolean[n][m];
        for (int y = 0; y < n; y++) {

            String line = sc.next();

            for (int x = 0; x < m; x++) {
                field[y][x] = line.charAt(x) - 48;
            }
        }

        System.out.println(bfs(field, check));
    }

    static int[][] pos = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static int bfs(int[][] field, boolean[][] check) {
        int ty = field.length - 1;
        int tx = field[0].length - 1;

        List<Item> anws = new ArrayList<>();

        Queue<Item> q = new PriorityQueue<>();
        check[0][0] = true;
        q.add(new Item(0, 0, 0));

        while (!q.isEmpty()) {
            Item item = q.remove();
            if (ty == item.y && tx == item.x) {
                anws.add(item);
            }
            for (int i = 0; i < 4; i++) {
                int dy = item.y + pos[i][0];
                int dx = item.x + pos[i][1];
                int cnt = item.cnt;

                if (inRange(field, dy, dx) && check[dy][dx] == false) {
                    check[dy][dx] = true;

                    if (field[dy][dx] == 1) {
                        q.add(new Item(dy, dx, cnt + 1));
                    }

                    if (field[dy][dx] == 0) {
                        q.add(new Item(dy, dx, cnt));
                    }
                }
            }
        }

        return anws.parallelStream().map(item -> item.cnt)
            .min(Integer::compareTo).get();

    }

    static boolean inRange(int[][] field, int y, int x) {
        int maxY = field.length;
        int maxX = field[0].length;

        if (y < 0 || y >= maxY) {
            return false;
        }

        if (x < 0 || x >= maxX) {
            return false;
        }
        return true;
    }

    static class Item implements Comparable<Item> {

        int y;
        int x;
        int cnt;


        public Item(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(cnt, o.cnt);
        }
    }
}
