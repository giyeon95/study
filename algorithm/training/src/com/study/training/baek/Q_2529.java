package com.study.training.baek;

import com.study.training.util.PrintUtils;
import java.util.Scanner;

public class Q_2529 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[] a = new char[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.next().toCharArray()[0];
        }
        int[] max = new int[n + 1];
        int[] min = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            max[i] = 9 - i;
            min[i] = i;
        }

        do {
            if (check(a, min)) {
                break;
            }
        } while (nextPermutation(min));

        do {
            if (check(a, max)) {
                break;
            }
        } while (beforePermutation(max));

        for (int i = 0; i < n + 1; i++) {
            System.out.print(max[i]);
        }
        System.out.println();

        for (int i = 0; i < n + 1; i++) {
            System.out.print(min[i]);
        }
        System.out.println();
    }

    private static boolean beforePermutation(int[] arr) {
        int i = arr.length - 1;
        int j = arr.length - 1;

        // 1. i> 0 && find(a[i-1] > a[i]), max(i)
        while (i > 0 && arr[i - 1] <= arr[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        // 2. find(a[j] < a[i-1]), max(j)
        while (arr[j] >= arr[i - 1]) {
            j--;
        }

        // 3. swap (a[j], swap[i-1]);
        swap(arr, j, i - 1);

        // 4. resort(i...i.length)
        resort(arr, i);

        return true;
    }

    private static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        int j = arr.length - 1;

        // 1. i > 0 && find(a[i-1] < a[i]), max(i)
        while (i > 0 && arr[i - 1] >= arr[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        //2. find(a[j] > a[i-1]), max(j);
        while (arr[j] <= arr[i - 1]) {
            j--;
        }
        //3. swap(a[i-1], a[j]);
        swap(arr, i - 1, j);

        //4. resort(i to a.length);
        resort(arr, i);
        return true;
    }

    private static void resort(int[] arr, int i) {
        int j = arr.length - 1;
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static boolean check(char[] a, int[] nums) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i] == '<' && nums[i] > nums[i + 1]) {
                return false;
            }

            if (a[i] == '>' && nums[i] < nums[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

