package com.study.training.baek;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Q_10973 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();

        int[] q = new int[size];

        for (int i = 0; i < size; i++) {
            q[i] = sc.nextInt();
        }

        int[] anw = nFindBeforePermutation(q);
        if (anw == null) {
            System.out.println(-1);
        } else {

            Stream.of(anw)
                .flatMapToInt(Arrays::stream)
                .boxed()
                .forEach(v -> System.out.print(v + " "));
        }

    }

    static int[] nFindBeforePermutation(int[] q) {
        int size = q.length;
        // 1. 뒤에서부터 내림차순이 아닌 내용을 찾는다.
        for (int n = size - 1; n > 0; n--) {
            if (q[n - 1] > q[n]) {
                int[] front = Arrays.copyOfRange(q, 0, n);
                int[] back = Arrays.copyOfRange(q, n, q.length);

                for (int i = back.length - 1; i >= 0; i--) {
                    if (front[front.length - 1] > back[i]) {
                        int temp = front[front.length - 1];
                        front[front.length - 1] = back[i];
                        back[i] = temp;
                        break;
                    }
                }

                int[] reverseBack = new int[back.length];
                for (int i = 0; i < back.length; i++) {
                    reverseBack[i] = back[back.length - i - 1];
                }

                int[] result = new int[front.length + back.length];

                System.arraycopy(front, 0, result, 0, front.length);
                System.arraycopy(reverseBack, 0, result, front.length, reverseBack.length);

                return result;
            }
        }

        return null;
    }

}
