package com.study.training.baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q_11004 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] a = new int[n];

        st = new StringTokenizer(br.readLine());

        int i = 0;
        while (st.hasMoreTokens()) {
            a[i++] = Integer.parseInt(st.nextToken());
        }
        System.out.println(quickSelect(a, 0, n - 1, k - 1));
    }

    private static int quickSelect(int[] arr, int l, int r, int select) {
        if (l == r) {
            return arr[l];
        }

//        int rndPivot = l + (int) Math.floor(Math.random() * (r - l + 1));
        int mid = partition(arr, l, r);

        if (mid == select) {
            return arr[mid];
        } else if (mid < select) {
            return quickSelect(arr, mid + 1, r, select);
        } else {
            return quickSelect(arr, l, mid - 1, select);
        }
    }


    public static int partition(int[] array, int left, int right) {
        int mid = (left + right) / 2;
        swap(array, left, mid); // 중앙 값을 첫 번째 요소로 이동

        int pivot = array[left];
        int i = left, j = right;

        while (i < j) {
            while (pivot < array[j]) { // j는 오른쪽에서 왼쪽으로 피봇보다 작거나 같은 값을 찾는다.
                j--;
            }

            while (i < j && pivot >= array[i]) { // i는 왼쪽에서 오른쪽으로 피봇보다 큰 값을 찾는다.
                i++;
            }
            swap(array, i, j); // 찾은 i와 j를 교환
        }
        // 반복문을 벗어난 경우는 i와 j가 만난경우
        // 피봇과 교환
        array[left] = array[i];
        array[i] = pivot;
        return i;
    }

 /*   private static int partition(int[] arr, int l, int r, int pivotIndex) {
        int pivot = arr[pivotIndex];
        swap(arr, r, pivotIndex);

        int pivotloc = l;

        for (int i = l; i < r; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, pivotloc);
                pivotloc++;
            }
        }

        swap(arr, r, pivotloc);
        return pivotloc;
    }
*/
    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

