package com.study.training.jongmanbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

// 난이도 중
public class WILDCARD {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int round = sc.nextInt(); // round;

        List<String> answer = new ArrayList<>();
        while (round-- > 0) {
            String word = sc.next();

            int n = sc.nextInt();

            String[] ws = new String[n];

            for (int i = 0; i < n; i++) {
                ws[i] = sc.next();
            }
            String[] solution = solution(ws, word);

            List<String> collect = Arrays.stream(solution)
                .filter(Objects::nonNull)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
            answer.addAll(collect);
        }

        answer.forEach(System.out::println);
    }

    public static String[] solution(String[] ws, String wild) {
        String[] anw = new String[ws.length];

        int i = 0;
        for (String s : ws) {
            if (match(wild, s)) {
                anw[i++] = s;
            }
        }

        return anw;
    }

    public static boolean match(String w, String s) {
        int pos = 0;

        while (pos < w.length() && pos < s.length() &&
            (w.charAt(pos) == '?' || s.charAt(pos) == w.charAt(pos))) {
            pos++;
        }

        if (w.length() == pos) {
            return s.length() == pos;
        }

        if (w.charAt(pos) == '*') {
            for (int skip = 0; skip + pos <= s.length(); skip++) {
                if (match(w.substring(pos + 1), s.substring(pos + skip))) {
                    return true;
                }
            }
        }

        return false;
    }

}
