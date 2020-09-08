package com.study.training.baek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Q_1260 {

    //정점의 개수
    static int nodeCnt;

    //간선의 개수
    static int routeCnt;

    static List<List<Integer>> node = new ArrayList<>();

    static boolean[] check;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        nodeCnt = sc.nextInt();
        routeCnt = sc.nextInt();

        // 탐색을 시작할 정점의 번호
        int startV = sc.nextInt() - 1;

        for (int i = 0; i < nodeCnt; i++) {
            node.add(new ArrayList<>());
        }

        for (int i = 0; i < routeCnt; i++) {

            int n = sc.nextInt() - 1;
            int m = sc.nextInt() - 1;

            node.get(n).add(m);
            node.get(m).add(n);
        }

        for (int i = 0; i < nodeCnt; i++) {
            Collections.sort(node.get(i));
        }


        check = new boolean[nodeCnt];
        dfs(startV);

        System.out.println();
        check = new boolean[nodeCnt];
        bfs(startV);

        System.out.println();
    }

    static void dfs(int pos) {

        check[pos] = true;
        System.out.print(pos + 1 + " ");
        for (int i = 0; i < node.get(pos).size(); i++) {

            int y = node.get(pos).get(i);
            if (check[y] == false) {
                dfs(y);
            }
        }
    }

    static void bfs(int startV) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startV);
        check[startV] = true;

        while (!queue.isEmpty()) {

            int x = queue.poll();
            System.out.print(x + 1 + " ");

            for (int i = 0; i < node.get(x).size(); i++) {
                int y = node.get(x).get(i);
                if (check[y] == false) {
                    check[y] = true;
                    queue.add(y);
                }
            }

        }
    }

}
