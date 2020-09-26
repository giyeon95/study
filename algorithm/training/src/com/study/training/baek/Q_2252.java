package com.study.training.baek;

import java.util.*;

public class Q_2252 {

    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int m = sc.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDepth = new int[n];

        // 초기화
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList());
        }

        for (int i = 0; i < m; i++) {
            int sp = sc.nextInt() - 1;
            int ep = sc.nextInt() - 1;

            graph.get(sp).add(ep);
            inDepth[ep]++;
        }
        int[] anw = topologySort(graph, inDepth);

        for (int i = 0; i < n; i++) {
            System.out.print(anw[i] + " ");
        }

    }

    static boolean[] check;

    static int[] topologySort(List<List<Integer>> graph, int[] inDepth) {
        int[] result = new int[n];
        check = new boolean[n];

        Queue<Integer> q = new LinkedList<>();
        int pos = 0;
        checkInEdgeCnt(q, inDepth);

        while (!q.isEmpty()) {
            int p = q.remove();
            result[pos++] = p + 1;
            List<Integer> list = graph.get(p);

            list.forEach(v -> {
                --inDepth[v];

                if (check[v] == false && inDepth[v] == 0) {
                    q.add(v);
                    check[v] = true;
                }
            });
        }

        return result;
    }

    static void checkInEdgeCnt(Queue<Integer> q, int[] inDepth) {
        for (int i = 0; i < n; i++) {
            if (check[i] == false && inDepth[i] == 0) {
                q.add(i);
                check[i] = true;
            }
        }
    }
}
