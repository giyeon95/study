package com.study.training.baek;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Q_1339 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = sc.next();
        }
        Map<Character, Integer> map = new HashMap<>();

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int digit = (int) Math.pow(10, word.length() - 1 - i);
                if (!map.containsKey(c)) {
                    map.put(c, digit);
                } else {
                    map.computeIfPresent(c, (k, v) -> v + digit);
                }
            }
        }

        List<Entry<Character, Integer>> entries =
            map.entrySet().stream()
                .sorted(Entry.<Character, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());

        Map<Character, Integer> mapCharToNum = new HashMap<>();
        int max = 9;
        for (Entry<Character, Integer> entry : entries) {
            mapCharToNum.put(entry.getKey(), max--);
        }

        int[] nums = new int[n];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String tmp = "";

            for (int j = 0; j < word.length(); j++) {
                int num = mapCharToNum.get(word.charAt(j));
                tmp += num;
            }
            nums[i] = Integer.parseInt(tmp);
        }

        int anw = 0;
        for (int num : nums) {
            anw += num;
        }
        System.out.println(anw);
    }
}
