package com.study.spring.template;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class CalcSumTest {

    Calculator calculator;
    File numFile;

    @BeforeEach
    void beforeEach() throws IOException {
        this.calculator = new Calculator();
        this.numFile = new ClassPathResource("files/numbers.txt").getFile();
    }

    @Test
    void sumOfNumbers() throws IOException {
        Integer sum = calculator.calcSum(numFile);
        assertThat(sum).isEqualTo(10);
    }

    @Test
    void multiplyOfNumbers() throws IOException {
        Integer multiply = calculator.calcMultiply(numFile);
        assertThat(multiply).isEqualTo(24);
    }

    @Test
    void concatenateStrings() throws IOException {
        String concatenate = calculator.concatenate(numFile);
        assertThat(concatenate).isEqualTo("1234");
    }
}
