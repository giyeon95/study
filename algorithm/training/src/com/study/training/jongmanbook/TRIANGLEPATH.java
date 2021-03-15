package com.study.training.jongmanbook;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// 난이도 하
public class TRIANGLEPATH {

    public static int[][] input;
    public static int line;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] anw = new int[n];

        int a = 0;
        while (n-- > 0) {
            line = sc.nextInt();
            input = new int[line][line];

            for (int i = 0; i < line; i++) {
                for (int j = 0; j <= i; j++) {
                    input[i][j] = sc.nextInt();
                }
            }

            anw[a++] = path(0, 0, 0);
        }

        Arrays.stream(anw).boxed().forEach(System.out::println);

    }

    public static int path(int y, int x, int sum) {
        if (y == line - 1) {
            return input[y][x] + sum;
        }

        sum += input[y][x];

        return Math.max(path(y + 1, x + 1, sum), path(y + 1, x, sum));
    }
}
