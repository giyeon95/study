package com.study.training.jongmanbook;

import java.util.Scanner;

/**
 * 4 FFFMMM MMMFFF FFFFF FFFFFFFFFF FFFFM FFFFFMMMMF MFMFMFFFMMMFMF MMFFFFFMFFFMFFFFFFMFFFMFFFFMFMMFFFFFFF
 */

// 팬미팅 난이도 상
public class FANMETTING {

    static char[] members;
    static char[] fans;
    static int memberSize;
    static int fanSize;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int qn = sc.nextInt();
        for (int i = 0; i < qn; i++) {
            String memberLine = sc.next();
            String fanLine = sc.next();

            memberSize = memberLine.length();
            fanSize = fanLine.length();
            members = new char[memberSize];
            fans = new char[fanSize];

            for (int j = 0; j < memberSize; j++) {
                members[j] = memberLine.charAt(j);
            }
            for (int j = 0; j < fanSize; j++) {
                fans[j] = fanLine.charAt(j);
            }

            System.out.println(solve());
        }
    }

    static int solve() {
        if (memberSize > fanSize) {
            return 0;
        }
        int anw = 0;

        for (int i = 0; i < fanSize - memberSize+ 1; i++) {
            boolean flag = false;
            for (int j = 0; j < memberSize; j++) {
                if (members[j] == 'M' && fans[j + i] == 'M') {
                    flag = true;
                }
            }
            if (flag == false) {
                anw++;
            }
        }
        return anw;
    }
}
