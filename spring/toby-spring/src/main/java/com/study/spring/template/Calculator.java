package com.study.spring.template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public Integer calcSum(File file) throws IOException {
        return lineReadTemplate(file,
            (line, value) ->
                value + Integer.parseInt(line),
            0);
    }

    public Integer calcMultiply(File file) throws IOException {

        return lineReadTemplate(file,
            (line, value) ->
                value * Integer.parseInt(line),
            1);
    }

    public Integer fileReadTemplate(File file, BufferedReaderCallback callback)
        throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return callback.doSomethingWithReader(br);
        }
    }

    public Integer lineReadTemplate(File file, LineCallback callback, int initVal)
        throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Integer res = initVal;
            String line;

            while ((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        }
    }
}
