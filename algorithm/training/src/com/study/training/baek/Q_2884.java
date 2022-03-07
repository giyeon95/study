package com.study.training.baek;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Q_2884 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int hour = sc.nextInt();
        int min = sc.nextInt();

        LocalTime localTime = LocalTime.of(hour, min);

        LocalTime minusLocalTime = localTime.minus(45, ChronoUnit.MINUTES);

        System.out.println(minusLocalTime.format(DateTimeFormatter.ofPattern("H m")));
    }

}
