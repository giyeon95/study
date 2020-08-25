package com.study.training;


public class SearchAlgorithm {

    //O(N^3)
    public static int inefficientMaxSum(int[] arrays) {
        int cnt = arrays.length;
        int ret = Integer.MIN_VALUE;

        for (int i = 0; i < cnt; i++) {
            for (int j = i; j < cnt; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arrays[k];
                    ret = Math.max(ret, sum);
                }
                System.out.println();
            }
        }
        return ret;
    }

    //O(N^2)
    public static int betterMaxSum(int[] arrays) {
        int cnt = arrays.length;
        int ret = Integer.MIN_VALUE;

        for (int i = 0; i < cnt; i++) {
            int sum = 0;
            for (int j = i; j < cnt; j++) {
                sum += arrays[j];
                ret = Math.max(ret, sum);
            }
        }
        return ret;
    }

    //O(NlgN)
    public static int fastMaxSum(int[] arrays, int lo, int hi) {
        if (lo == hi) {
            return arrays[lo];
        }

        int mid = (lo + hi) / 2;
        int left = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= lo; i--) {
            sum += arrays[i];
            left = Math.max(left, sum);
        }

        sum = 0;
        for (int j = mid + 1; j <= hi; j++) {
            sum += arrays[j];
            right = Math.max(right, sum);
        }

        int single = Math.max(fastMaxSum(arrays, lo, mid), fastMaxSum(arrays, mid + 1, hi));

        return Math.max(left + right, single);

    }

    //O(N)
    public static int fastestMaxSum(int[] arrays) {
        int cnt = arrays.length;
        int ret = Integer.MIN_VALUE;
        int psum = 0;
        for(int i = 0 ; i< cnt ; i++) {
            psum = Math.max(psum, 0) + arrays[i];
            ret = Math.max(psum, ret);
        }
        return ret;
    }

}
