package com.study.training.baek;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Q_2525 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int hour = sc.nextInt();
        int min = sc.nextInt();

        int addMin = sc.nextInt();

        LocalTime localTime = LocalTime.of(hour, min);

        LocalTime minusLocalTime = localTime.plus(addMin, ChronoUnit.MINUTES);

        System.out.println(minusLocalTime.format(DateTimeFormatter.ofPattern("H m")));
    }

}

