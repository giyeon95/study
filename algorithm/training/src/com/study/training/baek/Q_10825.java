package com.study.training.baek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Q_10825 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            students.add(new Student(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        Collections.sort(students);

        for (int i = 0; i < n; i++) {
            sb.append(students.get(i).name)
            .append("\n");
        }

        System.out.println(sb);
    }

    static class Student implements Comparable<Student> {

        String name;
        int korean;
        int english;
        int math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public int compareTo(Student o) {
            if(korean == o.korean) {
                if(english == o.english) {
                    if(math == o.math) {
                        return name.compareTo(o.name);
                    }
                    return Integer.compare(o.math, math);
                }
                return Integer.compare(english, o.english);
            }
            return Integer.compare(o.korean, korean);
        }
    }
}
