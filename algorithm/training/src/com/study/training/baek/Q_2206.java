package com.study.training.baek;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Q_2206 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] field = new int[n][m];

        for (int y = 0; y < n; y++) {
            String line = sc.next();

            for (int x = 0; x < m; x++) {
                field[y][x] = line.charAt(x) - '0';
            }
        }

        System.out.println(bfs(field));
    }

    static int[][] pos = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static int bfs(int[][] field) {
        int n = field.length;
        int m = field[0].length;
        boolean[][][] check = new boolean[n][m][2];

        List<Item> anwPools = new ArrayList<>();
        Queue<Item> q = new LinkedList<>();

        q.add(new Item(0, 0, 1, 0));
        check[0][0][0] = true;

        while (!q.isEmpty()) {
            Item item = q.remove();

            if (n - 1 == item.y && m - 1 == item.x) {
                anwPools.add(item);
            }

            for (int i = 0; i < 4; i++) {
                int dy = item.y + pos[i][0];
                int dx = item.x + pos[i][1];
                int cnt = item.cnt;

                int z = item.z;

                if (inRange(field, dy, dx)) {
                    if (check[dy][dx][z] == false && field[dy][dx] == 0) {
                        q.add(new Item(dy, dx, cnt + 1, z));
                        check[dy][dx][z] = true;
                    }

                    if (field[dy][dx] == 1 && z == 0 && check[dy][dx][z] == false) {
                        q.add(new Item(dy, dx, cnt + 1, 1));
                        check[dy][dx][1] = true;
                    }

                }

            }
        }

        return anwPools.parallelStream()
            .min(Item::compareTo)
            .map(i -> i.cnt)
            .orElse(-1);
    }

    static boolean inRange(int[][] field, int y, int x) {
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

    static class Item implements Comparable<Item> {

        int y;
        int x;
        int cnt;
        int z;

        public Item(int y, int x, int cnt, int z) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.z = z;
        }


        @Override
        public int compareTo(Item o) {
            return Integer.compare(cnt, o.cnt);
        }
    }
}
