package sn.ept.git.seminaire.cicd.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class A_CalculatorTest {

    private static ICalculator calculator;
    private  double a,b;
    private static  Random r ;


    @BeforeAll
    static void beforeAll(){
        log.info("Something to do before all tests");
        calculator = new Calculator();
        r = new Random();
    }


    @BeforeEach
      void beforeEach(){
        log.info("Something to do before each test");
        a=11;
        b=22;
    }

    @Test
    void add() {
        assertThat(1).isEqualTo(1);
    }

    @Test
    void subtract() {
        assertThat(1).isEqualTo(1);
    }

    @Test
    void multiply() {
        assertThat(1).isEqualTo(1);
    }

    @Test
    void divide() {
        assertThat(1).isEqualTo(1);
    }

    @Test
    void divisionByZeroShouldThrowError() {
        assertThat(1).isEqualTo(1);
    }

    static Stream<Arguments> addTestData() {
        return IntStream
                .range(1,20)
                .mapToObj(item->Arguments.of(r.nextDouble(), r.nextDouble()));
    }

    @DisplayName("ICalculator: parameterized test for add method")
    @ParameterizedTest
    @MethodSource("addTestData")
    void testAddOperationWithParameterizedData(double a, double b) {
        assertThat(1).isEqualTo(1);
    }

    @RepeatedTest(value = 5)
    void addRepeted() {
        assertThat(1).isEqualTo(1);
    }
}