package com.study.training.baek;

import java.util.*;

public class Q_1766 {

    static int n;
    static int m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        int[] inEdgeCnt = new int[n];

        List<List<Integer>> nodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int sn = sc.nextInt() - 1;
            int en = sc.nextInt() - 1;

            nodes.get(sn).add(en);
            ++inEdgeCnt[en];
        }

        int[] anw = topologySort(nodes, inEdgeCnt);

        for (int i = 0; i < anw.length; i++) {
            System.out.print(anw[i] + " ");
        }

    }


    static int[] topologySort(List<List<Integer>> nodes, int[] inEdgeCnt) {
        Queue<Integer> q = new PriorityQueue<>();
        boolean[] check = new boolean[n];
        int[] anw = new int[n];
        int pos = 0;

        for (int i = 0; i < inEdgeCnt.length; i++) {
            if (inEdgeCnt[i] == 0) {
                q.add(i);
                check[i] = true;
            }
        }

        while (!q.isEmpty()) {
            int p = q.remove();
            anw[pos++] = p + 1;
            List<Integer> nextNodes = nodes.get(p);

            nextNodes.forEach(v -> {
                --inEdgeCnt[v];

                if (check[v] == false && inEdgeCnt[v] == 0) {
                    q.add(v);
                    check[v] = true;
                }
            });
        }
        return anw;
    }
}
