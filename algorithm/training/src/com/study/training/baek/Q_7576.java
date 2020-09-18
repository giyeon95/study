package com.study.training.baek;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Stream;

public class Q_7576 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] field = new int[n][m];
        dist = new int[n][m];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                field[y][x] = sc.nextInt();
            }
        }

        System.out.println(bfs(field));
    }

    static int pos[][] = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    static int bfs(int[][] field) throws Exception {
        Queue<Item> queue = new LinkedList<>();

        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == 1) {
                    dist[y][x] = 0;
                    queue.add(new Item(y, x));
                }
            }
        }

        while (!queue.isEmpty()) {
            Item item = queue.poll();

            for (int i = 0; i < 4; i++) {
                int dy = item.y + pos[i][0];
                int dx = item.x + pos[i][1];
                if (isValidRange(field, dy, dx)) {
                    if (field[dy][dx] == 0) {
                        field[dy][dx] = 1;
                        dist[dy][dx] = dist[item.y][item.x] + 1;
                        queue.add(new Item(dy, dx));
                    }

                }
            }
        }

        boolean present = Stream.of(field)
            .flatMapToInt(Arrays::stream)
            .boxed()
            .anyMatch(i -> i == 0);

        if (present) {
            return -1;
        }

        int max = Stream.of(dist)
            .flatMapToInt(Arrays::stream)
            .boxed()
            .max(Integer::compareTo).orElseThrow(Exception::new);

        return max;

    }

    static int[][] dist;


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

        int y;
        int x;

        public Item(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
