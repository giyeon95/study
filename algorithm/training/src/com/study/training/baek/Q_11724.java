package com.study.training.baek;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q_11724 {

    static List<List<Integer>> nodes = new ArrayList<>();
    static boolean[] check;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nodeCnt = sc.nextInt();
        int edgeCnt = sc.nextInt();

        check = new boolean[nodeCnt];

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

        int graphCnt = 0;
        for (int i = 0; i < nodeCnt; i++) {
            if (check[i] == false) {
                dfs(i);
                graphCnt++;
            }
        }

        System.out.println(graphCnt);
    }

    static void dfs(int pos) {
        check[pos] = true;

        for (int i = 0; i < nodes.get(pos).size(); i++) {

            int y = nodes.get(pos).get(i);
            if (check[y] == false) {
                dfs(y);
            }
        }
    }
}
