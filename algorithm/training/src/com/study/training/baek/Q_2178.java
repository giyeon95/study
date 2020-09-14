package com.study.training.baek;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q_2178 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        check = new boolean[n][m];
        int[][] field = new int[n][m];
        dist = new int[n][m];

        for (int y = 0; y < n; y++) {
            String s = sc.next();

            for (int x = 0; x < s.length(); x++) {
                field[y][x] = s.charAt(x) - 48;
            }
        }

        System.out.println(bfs(field));

    }

    static boolean check[][];
    static int dist[][];
    static int[][] pos = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    static int bfs(int[][] field) {

        Queue<Item> q = new LinkedList<>();
        check[0][0] = true;
        q.add(new Item(0, 0, 1));
        dist[0][0] = 1;

        int sizeY = field.length;
        int sizeX = field[0].length;

        while (!q.isEmpty()) {
            Item item = q.poll();

            for (int i = 0; i < 4; i++) {
                int dy = item.y + pos[i][0];
                int dx = item.x + pos[i][1];

                if (isValidRange(field, dy, dx)) {
                    if (field[dy][dx] == 1 && check[dy][dx] == false) {
                        check[dy][dx] = true;
                        dist[dy][dx] = dist[item.y][item.x] + 1;
                        q.add(new Item(dy, dx, item.depth + 1));
                    }
                }

            }
        }

        return dist[sizeY - 1][sizeX - 1];
    }

    static boolean isValidRange(int[][] field, int y, int x) {
        if (y >= field.length || y < 0) {
            return false;
        }

        if (x >= field[y].length || x < 0) {
            return false;
        }

        return true;
    }

    static class Item {

        private int y;
        private int x;
        private int depth;

        Item(int y, int x, int depth) {
            this.y = y;
            this.x = x;
            this.depth = depth;
        }

    }
}
