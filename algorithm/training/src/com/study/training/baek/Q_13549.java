package com.study.training.baek;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q_13549 {

    final static int MAX = 100001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        System.out.println(dfs(n, k));
    }

    static int dfs(int n, int k) {
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> nextQueue = new LinkedList<>();
        boolean[] check = new boolean[MAX];

        int t = 0;
        q.add(n);
        check[n] = true;

        while (!q.isEmpty()) {
            int x = q.remove();

            check[x] = true;

            if (x == k) {
                return t;
            }

            int pos1 = x * 2;
            if (inRange(pos1) && !check[pos1]) {
                q.add(pos1);
                check[pos1] = true;
            }

            int pos2 = x - 1;
            if (inRange(pos2) && !check[pos2]) {
                nextQueue.add(pos2);
                check[pos2] = true;
            }

            int pos3 = x + 1;
            if (inRange(pos3) && !check[pos3]) {
                nextQueue.add(pos3);
                check[pos3] = true;
            }

            if (q.isEmpty()) {
                q.addAll(nextQueue);
                nextQueue = new LinkedList<>();
                t++;
            }
        }

        return -1;

    }

    static boolean inRange(int x) {

        if (x >= MAX || x < 0) {
            return false;
        }
        return true;
    }

}
