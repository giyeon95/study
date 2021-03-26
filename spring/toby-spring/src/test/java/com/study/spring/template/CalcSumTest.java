package com.study.spring.template;

import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class CalcSumTest {

    @Test
    void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        Resource resource = new ClassPathResource("files/numbers.txt");

        Integer sum = calculator.calcSum(resource.getFile());
        Assertions.assertThat(sum).isEqualTo(10);
    }
}
