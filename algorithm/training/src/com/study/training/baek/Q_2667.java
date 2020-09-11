package com.study.training.baek;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q_2667 {


    static int[][] input;
    static boolean[][] check;
    static List<Integer> keys = new ArrayList<>();

    static int[][] position = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        input = new int[n][n];
        check = new boolean[n][n];

        int cnt = 0;

        for (int i = 0; i < n; i++) {

            String s = sc.next();

            for (int j = 0; j < s.length(); j++) {
                input[i][j] = s.charAt(j) - 48;
            }
        }

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == 1 && !check[i][j]) {
                    dfs(i, j, ++cnt);
                }
            }
        }

        System.out.println(cnt);

        List<Long> houseCnt = new ArrayList<>();

        for (int i = 1; i <= cnt; i++) {
            int num = i;

            Long count = keys.stream()
                .filter(key -> key == num)
                .count();

            houseCnt.add(count);
        }

        houseCnt.stream()
            .sorted()
            .forEach(System.out::println);

    }


    static void dfs(int y, int x, int key) {
        check[y][x] = true;
        keys.add(key);

        for (int i = 0; i < 4; i++) {
            int ny = y + position[i][0];
            int nx = x + position[i][1];

            if (!((ny < 0 || nx < 0) || (ny >= n || nx >= n))) {
                if (input[ny][nx] == 1 && !check[ny][nx]) {
                    dfs(ny, nx, key);
                }

            }
        }
    }
}
