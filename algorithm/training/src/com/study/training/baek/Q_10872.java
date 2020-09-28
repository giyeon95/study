package com.study.training.baek;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Q_10872 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int anw = IntStream.range(1, n + 1)
            .reduce(1, (subtotal, element) -> subtotal * element);
        System.out.println(anw);
    }
}
