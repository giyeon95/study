package com.study.training.baek;

import java.util.Scanner;

public class Q_14426 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int cnt = 0;

        String[] sentences = new String[n];
        String[] words = new String[m];

        for (int i = 0; i < n; i++) {
            sentences[i] = sc.next();
        }

        for (int i = 0; i < m; i++) {
            words[i] = sc.next();
        }

        for (String word : words) {
            for (String sentence : sentences) {
                if (sentence.startsWith(word)) {
                    cnt++;
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
