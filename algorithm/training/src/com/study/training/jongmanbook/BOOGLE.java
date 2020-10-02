package com.study.training.jongmanbook;


public class BOOGLE {

    //보글게임 난이도 하
    // 주어진 단어가 있으면 true, 없으면 false 를 출력하세요.
    public static char[][] board = new char[][]{
        {'U', 'R', 'L', 'P', 'M'},
        {'X', 'P', 'R', 'E', 'T'},
        {'G', 'I', 'A', 'E', 'T'},
        {'X', 'T', 'N', 'Z', 'Y'},
        {'X', 'O', 'Q', 'R', 'S'}};

    public static void main(String[] args) {

        String words = "GIRL";
//        for (int i = 0; i < words.length(); i++) {
//            hasNext(i, i, words);
//

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (hasNext(y, x, words)) {
                    System.out.println(true);

                }
            }
        }

    }


    public static int[] dx = new int[]{-1, -1, -1, 1, 1, 1, 0, 0};
    public static int[] dy = new int[]{-1, 0, 1, -1, 0, 1, -1, 1};

    public static boolean inRange(int y, int x) {
        if (y < 0 || x < 0) {
            return false;
        }

        if(y >= 5 || x >= 5) {
            return false;
        }
        return true;
    }

    public static boolean hasNext(int y, int x, String words) {
        if (!inRange(y, x)) {
            return false;
        }

        if (board[y][x] != words.charAt(0)) {
            return false;
        }

        if (words.length() == 1) {
            return true;
        }

        for (int direction = 0; direction < 8; direction++) {
            int nextY = y + dy[direction];
            int nextX = x + dx[direction];

            if (hasNext(nextY, nextX, words.substring(1))) {
                return true;
            }
        }

        return false;
    }


}
