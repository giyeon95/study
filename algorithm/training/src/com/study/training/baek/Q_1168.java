package com.study.training.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q_1168 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]) - 1;
        StringBuilder sb = new StringBuilder("<");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i + 1);
        }

        int i = 0;
        while (!list.isEmpty()) {

            i += k;
            if (i >= list.size()) {
                i %= list.size();
            }

            int p = list.remove(i);
            sb.append(p)
                .append(", ");

        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append(">");

        System.out.println(sb);
    }
}
