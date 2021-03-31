package com.study.spring.template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.logging.log4j.util.Strings;

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

    public <T> T lineReadTemplate(File file, LineCallback<T> callback, T initVal)
        throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            T res = initVal;
            String line;

            while ((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        }
    }

    public String concatenate(File file) throws IOException {
        return lineReadTemplate(file,
            (line, value) ->
                value + line,
            Strings.EMPTY);
    }
}
