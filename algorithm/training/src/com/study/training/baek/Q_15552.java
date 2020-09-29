package com.study.training.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_15552 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");

            sb.append(Integer.parseInt(s[0])
                + Integer.parseInt(s[1]) + "\n"
            );
        }

        System.out.println(sb);

    }
}
