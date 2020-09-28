package com.study.training.baek;

import com.study.training.util.PrintUtils;
import java.util.*;
import java.util.stream.Stream;

public class Q_2056 {

    static int n;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        List<List<Integer>> nodes = new ArrayList<>();
        int[] inEdgeCnt = new int[n];
        int[] nodeCosts = new int[n];

        for (int i = 0; i < n; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            int cost = sc.nextInt();
            int precedeCnt = sc.nextInt();

            nodeCosts[i] = cost;

            for (int j = 0; j < precedeCnt; j++) {
                int preNode = sc.nextInt() - 1;

                nodes.get(i).add(preNode);

                ++inEdgeCnt[preNode];
            }
        }

        System.out.println(getMinCost(nodes, inEdgeCnt, nodeCosts));
    }

    static int getMinCost(List<List<Integer>> nodes, int[] inEdgeCnt, int[] nodeCosts)
        throws Exception {
        int[] cost = new int[nodeCosts.length];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < inEdgeCnt.length; i++) {
            if (inEdgeCnt[i] == 0) {
                q.add(i);
                cost[i] = nodeCosts[i];
            }
        }

        while (!q.isEmpty()) {
            int p = q.remove();
            nodes.get(p).forEach(v -> {
                // 이전노드 p, 다음 노드 v
                if (cost[v] < cost[p] + nodeCosts[v]) {
                    cost[v] = cost[p] + nodeCosts[v];
                }

                if (--inEdgeCnt[v] == 0) {
                    q.add(v);
                }
            });
        }

        return Stream.of(cost)
            .flatMapToInt(Arrays::stream)
            .boxed()
            .max(Integer::compareTo)
            .orElseThrow(Exception::new);
    }
}
