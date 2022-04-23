package sn.ept.git.seminaire.cicd.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class B_TripleAPrincipal {

    private static ICalculator calculator;


    @BeforeAll
    static void beforeAll(){
        calculator = new Calculator();
    }

    @Test
    void shouldRespectTripleAPrinciple() {
        //arrange
        double a=100,b=200;
        double expected=a+b;

        //act
        double result = calculator.add(a,b);

        //assert
        assertThat(result).isEqualTo(expected);
    }

}