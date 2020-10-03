package com.study.training.baek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Q_2751 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        List<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }
        Collections.sort(a);
        for (int i = 0; i < n; i++) {
            sb.append(a.get(i) + "\n");
        }

        System.out.println(sb);
    }
}
