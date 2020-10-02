package com.study.training.jongmanbook;

import java.util.Scanner;

//쿼드 트리 뒤집기 난이도 하
// 풀이 참조 o, 코드 참조 o
public class QUADTREE {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String[] q = new String[4];
        for (int i = 0; i < n; i++) {
            q[i] = sc.next();
        }

        for (int i = 0; i < n; i++) {
            pos = -1;
            String s = reverse(q[i]);
            System.out.println(s);
        }

    }

    static int pos = 0;

    static String reverse(String str) {
        pos++;
        char head = str.charAt(pos);
        if (head == 'b' || head == 'w') {
            return head + "";
        }
        String upperLeft = reverse(str);
        String upperRight = reverse(str);
        String lowerLeft = reverse(str);
        String lowerRight = reverse(str);

        return "x" + lowerLeft + lowerRight + upperLeft + upperRight;
    }
}
