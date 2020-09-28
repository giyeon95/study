package com.study.training.util;

import java.util.List;

public class PrintUtils {

    private PrintUtils() {
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


    public static void print(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void print(boolean[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


    public static void print(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void print(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void print(List<List<Integer>> lists) {
        for (int i = 0; i < lists.size(); i++) {
            System.out.print(i + " : ");
            lists.get(i).forEach(v -> {
                System.out.print(v + " ");
            });
            System.out.println();
        }
    }

    public static void printValue(int[] array, int pos) {
        System.out.println("[" + pos + "] = " + array[pos]);
    }

    public static void printValue(boolean[] array, int pos) {
        System.out.println("[" + pos + "] = " + array[pos]);
    }

    public static void printValue(String[] array, int pos) {
        System.out.println("[" + pos + "] = " + array[pos]);
    }

    public static void printValue(int[][] array, int y, int x) {
        System.out.println("arr[" + y + "][" + x + "]" + " = " + array[y][x]);
    }

    public static void printValue(boolean[][] array, int y, int x) {
        System.out.println("arr[" + y + "][" + x + "]" + " = " + array[y][x]);
    }

    public static void printValue(String[][] array, int y, int x) {
        System.out.println("arr[" + y + "][" + x + "]" + " = " + array[y][x]);
    }


}
