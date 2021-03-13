package com.study.training.programmers.skilltest;

import java.util.LinkedList;
import java.util.Stack;

public class Q_01 {

    public static void main(String[] args) {

    }

    boolean solution(String s) {
        Stack<Character> stk = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(c == '(') {
                stk.push(c);
            }

            if(c == ')') {
                if(stk.isEmpty()) {
                    return false;
                }

                stk.pop();
            }
        }

        return stk.isEmpty();
    }
}
