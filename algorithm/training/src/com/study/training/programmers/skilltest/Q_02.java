package com.study.training.programmers.skilltest;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Q_02 {

    public static Map<String, String> map = new HashMap<>() {
        {
            put("C", "a");
            put("C#", "b");
            put("D", "c");
            put("D#", "d");
            put("E", "e");
            put("F", "f");
            put("F#", "g");
            put("G", "h");
            put("G#", "i");
            put("A", "j");
            put("A#", "k");
            put("B", "l");
        }
    };

    public static void main(String[] args) {
        String abcdefg = solution("ABCDEFG",
            new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"});
        System.out.println(abcdefg);
    }

    public static String solution(String m, String[] musicinfos) {

        for (String musicinfo : musicinfos) {
            String[] infos = musicinfo.split(",");

            String stTime = infos[0];
            String edTime = infos[1];
            String name = infos[2];
            String codes = infos[3];

            String convertCode = converter(codes);
            String inputConvertCode = converter(m);

            LocalTime stLt = LocalTime.parse(stTime, DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime etLt = LocalTime.parse(edTime, DateTimeFormatter.ofPattern("HH:mm"));

            int minute = Math.toIntExact(ChronoUnit.MINUTES.between(stLt, etLt));

            StringBuilder fullCode = new StringBuilder();
            for (int i = 0; i < minute; i += convertCode.length()) {
                fullCode.append(convertCode);
            }

            String s = fullCode.toString();
            if (minute < convertCode.length()) {
                s = s.substring(0, minute);
            }

            if (s.contains(inputConvertCode)) {
                return name;
            }
        }

        return "(None)";
    }

    public static String converter(String codes) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < codes.length(); i++) {

            if (i + 1 < codes.length() && codes.charAt(i + 1) == '#') {
                sb.append(map.get(
                    String.valueOf(codes.charAt(i)) + codes.charAt(i + 1)));
            } else {
                sb.append(map.get(
                    String.valueOf(codes.charAt(i))));
            }
        }
        return sb.toString();
    }
}
