package com.study.training.baek;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q_1707 {

    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        int caseCnt = sc.nextInt();
        String[] anws = new String[caseCnt];

        for (int i = 0; i < caseCnt; i++) {
            anws[i] = initQuestion();
        }

        for (int i = 0; i < anws.length; i++) {
            System.out.println(anws[i]);
        }

    }

    static List<List<Integer>> nodes;
    static boolean[] check;
    static int[] colors;
    static int graphCnt;

    static String initQuestion() {

        nodes = new ArrayList<>();

        int nodeCnt = sc.nextInt();
        int edgeCnt = sc.nextInt();

        check = new boolean[nodeCnt];
        colors = new int[nodeCnt];
        graphCnt = 0;

        //init nodes
        for (int i = 0; i < nodeCnt; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < edgeCnt; i++) {
            int node1 = sc.nextInt() - 1;
            int node2 = sc.nextInt() - 1;

            nodes.get(node1).add(node2);
            nodes.get(node2).add(node1);
        }

        for (int i = 0; i < nodeCnt; i++) {
            if (check[i] == false) {
                dfs(i, -1);
                graphCnt++;
            }
        }

        for (int i = 0; i < nodes.size(); i++) {
            int startColor = colors[i];

            for (int j = 0; j < nodes.get(i).size(); j++) {
                int y = nodes.get(i).get(j);
                int checkColor = colors[y];
                if (startColor == checkColor) {
                    return "NO";
                }
            }
        }

        return "YES";
    }


    static void dfs(int pos, int color) {

        check[pos] = true;
        colors[pos] = color;

        for (int i = 0; i < nodes.get(pos).size(); i++) {
            int y = nodes.get(pos).get(i);

            if (check[y] == false) {
                dfs(y, color * -1);
            }
        }
    }

}
