package com.study.training.baek;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Q_10972 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] target = new int[n];
        for (int i = 0; i < target.length; i++) {
            target[i] = sc.nextInt();
        }

        int[] anw = nextPermutation(target);

        if (anw == null) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < n; i++) {
                System.out.print(anw[i] + " ");
            }
            System.out.println();
        }
        
    }


    static int[] nextPermutation(int[] input) {

        int lx = input.length - 1;

        for (int n = lx; n > 0; n--) {
            if (input[n - 1] < input[n]) {

                int[] a = Arrays.copyOf(input, n);
                int[] b = Arrays.copyOfRange(input, n, input.length);

                int targetNum = input[a.length - 1];

                for (int m = b.length - 1; m >= 0; m--) {
                    if (targetNum < b[m]) {

                        int temp = b[m];
                        b[m] = a[a.length - 1];
                        a[a.length - 1] = temp;

                        List<Integer> lists = Stream.of(a)
                            .flatMapToInt(Arrays::stream)
                            .boxed()
                            .collect(Collectors.toList());

                        Arrays.sort(b);
                        for (int j : b) {
                            lists.add(j);
                        }

                        return lists.stream().mapToInt(Integer::intValue).toArray();
                    }
                }

            }

        }
        return null;
    }
}
