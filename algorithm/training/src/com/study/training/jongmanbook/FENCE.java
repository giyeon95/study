package com.study.training.jongmanbook;

import java.util.Scanner;

public class FENCE {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int qn = sc.nextInt();
        int[] anws = new int[qn];

        for (int i = 0; i < qn; i++) {
            int n = sc.nextInt();
            h = new int[n];

            for (int j = 0; j < n; j++) {
                h[j] = sc.nextInt();
            }
            anws[i] = solve(0, n - 1);
        }

        for (int i = 0; i < anws.length; i++) {
            System.out.println(anws[i]);
        }
    }

    static int[] h;

    static int solve(int left, int right) {
        if (left == right) {
            return h[left];
        }

        int mid = (left + right) / 2;
        int ret = Math.max(solve(left, mid), solve(mid + 1, right));

        int lo = mid;
        int hi = mid + 1;
        int height = Math.min(h[lo], h[hi]);

        ret = Math.max(ret, height * 2);
        while (left < lo || hi < right) {
            if (hi < right && (lo == left || h[lo - 1] < h[hi + 1])) {
                ++hi;
                height = Math.min(height, h[hi]);
            } else {
                --lo;
                height = Math.min(height, h[lo]);
            }

            ret = Math.max(ret, height * (hi - lo + 1));
        }
        return ret;
    }

}
