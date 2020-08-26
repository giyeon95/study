package com.study.training.beginner;

import java.util.Scanner;

public class BOARDCOVER {

    // Board Game 난이도 하
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] board;

        int round = sc.nextInt();

        for (int cnt = 0; cnt < round; cnt++) {
            int h = sc.nextInt();
            int w = sc.nextInt();
            board = new int[h][w];
            for (int j = 0; j < h; j++) {

                String line = sc.next();
                for (int c = 0; c < line.length(); c++) {
                    if (line.charAt(c) == '#') {
                        board[j][c] = 1;
                    } else {
                        board[j][c] = 0;
                    }
                }
            }
            if (isValidate(board)) {
                System.out.println(cover(board));

            } else {
                System.out.println(0);
            }


        }
    }

    private static int cover(int[][] board) {
        int y = -1;
        int x = -1;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    y = i;
                    x = j;
                    break;
                }
            }
            if (y != -1) {
                break;
            }
        }

        if (y == -1) {
            return 1;
        }

        int ret = 0;
        for (int type = 0; type < 4; type++) {
            if (set(board, y, x, type, 1)) {
                ret += cover(board);
            }

            set(board, y, x, type, -1);
        }
        return ret;
    }

    public static boolean set(int[][] board, int y, int x, int type, int delta) {
        boolean ok = true;
        for (int i = 0; i < 3; i++) {
            final int ny = y + blockType[type][i][0];
            final int nx = x + blockType[type][i][1];
            if (!inRange(ny, nx, board)) {
                ok = false;
            } else if ((board[ny][nx] += delta) > 1) {
                ok = false;
            }
            // todo 교재 참고

        }
        return ok;
    }

    public static int[][][] blockType = new int[][][]{
        {{0, 0}, {1, 0}, {0, 1}},
        {{0, 0}, {0, 1}, {1, 1}},
        {{0, 0}, {1, 0}, {1, 1}},
        {{0, 0}, {1, 0}, {1, -1}}
    };


    public static boolean inRange(int y, int x, int[][] boards) {
        if (y < 0 || x < 0) {
            return false;
        }

        if (y >= boards.length || x >= boards[y].length) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidate(int[][] board) {
        int cnt = 0;

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (board[y][x] == 0) {
                    cnt++;
                }
            }
        }

        if (cnt % 3 == 0) {
            return true;
        } else {
            return false;
        }
    }


}
