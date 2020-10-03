package com.study.training.baek;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Q_11652 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Long, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Long inNum = sc.nextLong();
            if (!map.containsKey(inNum)) {
                map.put(inNum, 0);
            }
            map.computeIfPresent(inNum, (k, v) -> ++v);
        }
        Long minKey = -1L;
        int maxValue = -1;

        Set<Long> keys = map.keySet();

        for (Long key : keys) {
            int value = map.get(key);
            if (maxValue < value) {
                maxValue = value;
                minKey = key;
            }
            if (maxValue == value) {
                if (minKey > key) {
                    minKey = key;
                }
            }
        }
        System.out.println(minKey);
    }
}
