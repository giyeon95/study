package com.study.training.baek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Q_11650 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        List<Pair> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new Pair(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(a);

        for (int i = 0; i < n; i++) {
            Pair p = a.get(i);
            sb.append(p.x)
                .append(" ")
                .append(p.y)
                .append("\n");
        }
        System.out.println(sb);
    }

    static class Pair implements Comparable<Pair> {

        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair o) {
            if (x == o.x) {
                return Integer.compare(y, o.y);
            }
            return Integer.compare(x, o.x);
        }
    }
}
