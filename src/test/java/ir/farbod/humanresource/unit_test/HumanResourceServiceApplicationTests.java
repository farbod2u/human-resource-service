package ir.farbod.humanresource.unit_test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class HumanResourceServiceApplicationTests {

    Calculator testCalculator = new Calculator();

    @Test
    void testCalculatorAdd() {
        // Given
        int number1 = 20;
        int number2 = 40;

        // When
        int result = testCalculator.add(number1, number2);

        // Then
		int expected = 60;
		assertThat(result).isEqualTo(expected);
    }


    class Calculator {
        int add(int number1, int number2) {
            return number1 + number2;
        }
    }
}
