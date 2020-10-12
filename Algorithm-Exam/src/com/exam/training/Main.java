package com.exam.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = sc.nextInt();
        }

        int[] solution = solution(input);

        for (int i = 0; i < solution.length; i++) {
            System.out.println(" solution[i] = " + solution[i]);
        }
    }


    static int[] solution(int[] numbers) {

        List<Integer> list = new ArrayList<>();
        int size = numbers.length;
        for (int i = 0; i < size; i++) {
            int stn = numbers[i];

            for (int j = i + 1; j < size; j++) {
                int sen = numbers[j];

                list.add(stn + sen);
            }
        }

        int[] anw = list.stream().collect(Collectors.toSet())
            .stream()
            .mapToInt(Integer::intValue)
            .boxed()
            .mapToInt(t -> t)
            .toArray();

        Arrays.sort(anw);

        return anw;
    }


}
