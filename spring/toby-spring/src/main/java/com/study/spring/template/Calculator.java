package com.study.spring.template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public Integer calcSum(File file) throws IOException {
        return fileReadTemplate(file, br -> {
            int sum = 0;

            String line;
            while ((line = br.readLine()) != null) {
                sum += Integer.parseInt(line);
            }
            return sum;
        });
    }

    public Integer fileReadTemplate(File file, BufferedReaderCallback callback)
        throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return callback.doSomethingWithReader(br);
        }
    }
}
