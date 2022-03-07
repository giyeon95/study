package com.study.training.baek;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Q_10871 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int standardValue = sc.nextInt();

        String result = IntStream.range(0, size)
            .map(i -> sc.nextInt())
            .filter(input -> input < standardValue)
            .boxed()
            .map(String::valueOf)
            .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}
