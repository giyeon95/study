package com.study.training.beginner;

import java.util.Scanner;

public class PICNIC {


    public static boolean friends[][];

    //난이도 하
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int quizCnt = sc.nextInt();

        for (int i = 0; i < quizCnt; i++) {
            int studentCnt = sc.nextInt(); //학생수

            friends = new boolean[studentCnt][studentCnt];

            int pairCnt = sc.nextInt(); // 친구 쌍의 수
            createQuiz(studentCnt, pairCnt);
        }
    }

    public static void createQuiz(int studentCnt, int pairCnt) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < pairCnt; i++) {

            int y = -1;
            for (int j = 0; j < 2; j++) {

                int input = sc.nextInt();
                if (j == 0) {
                    y = input;
                } else if (j == 1) {
                    friends[y][input] = true;
                    friends[input][y] = true;
                }
            }
        }
        boolean[] taken = new boolean[studentCnt];
        System.out.println("pairCnt = " + countPairings(taken));
    }

    public static int countPairings(boolean[] taken) {

        // 남은 학생들중 가장 번호가 빠른 학생을 찾는다
        int firstFree = -1;

        // 학생수
        int cnt = taken.length;

        for (int i = 0; i < cnt; i++) {
            if (!taken[i]) {
                firstFree = i;
                break;
            }
        }
        // 모든 학생들이 짝을 찾았으면 한가지 방법을 찾았으니 종료
        if (firstFree == -1) {
            return 1;
        }

        int ret = 0;
        //이 학생과 짝을 지을 학생을 결정
        for (int pairWith = firstFree + 1; pairWith < cnt; pairWith++) {

            if (!taken[pairWith] && friends[firstFree][pairWith]) {
                taken[firstFree] = true;
                taken[pairWith] = true;

                ret += countPairings(taken);
                taken[firstFree] = false;
                taken[pairWith] = false;
            }
        }
        return ret;
    }

}
