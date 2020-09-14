package com.study.training.baek;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q_1697 {

    static final int MAX = 100001;

    static boolean[] check = new boolean[MAX];
    static int[] dist = new int[MAX];


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        System.out.println(dfs(n, k));
    }


    static int dfs(int start, int dest) {
        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        check[start] = true;
        dist[start] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();
            int nd = dist[now];

            int pos = now + 1;
            if (isValidRange(pos) && check[pos] == false) {
                q.add(pos);
                dist[pos] = nd + 1;
                check[pos] = true;
            }

            int pos2 = now - 1;
            if (isValidRange(pos2) && check[pos2] == false) {
                q.add(pos2);
                dist[pos2] = nd + 1;
                check[pos2] = true;
            }

            int pos3 = now * 2;
            if (isValidRange(pos3) && check[pos3] == false) {
                q.add(pos3);
                dist[pos3] = nd + 1;
                check[pos3] = true;
            }

            if (pos == dest) {
                return dist[pos];
            } else if (pos2 == dest) {
                return dist[pos2];
            } else if (pos3 == dest) {
                return dist[pos3];
            }
        }

        return -1;
    }

    static boolean isValidRange(int pos) {
        if (pos >= MAX) {
            return false;
        }
        if (pos < 0) {
            return false;
        }
        return true;
    }

}
