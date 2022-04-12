package sn.ept.git.seminaire.cicd.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import sn.ept.git.seminaire.cicd.demo.exception.BadPhoneException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class E_FirstPrincipalTest {

    private static ICalculator calculator;
    private static double resultOne, resultTwo;
    private double a, b;

    @BeforeAll
    static void beforeAll() {
        log.info("Something to do before all tests");
        calculator = new Calculator();
        resultOne = 33;
    }

    @BeforeEach
    void beforeEach() {
        log.info("Something to do before each test");
        a = 11;
        b = 22;
    }

    /**
     * A developer should not hesitate to run the tests as they are slow.
     * You should be aiming for many hundreds or thousands of tests per second.
     * => SRP principal
     */
    @Nested
    class Fast {

        @RepeatedTest(100)
        void addShouldReturnTheSumOfPositiveNumbers() {
            double provided = calculator.add(b, a);
            double expected = a + b;
            assertThat(provided).isEqualTo(expected);
        }
    }

    /**
     * You can isolate them from interfering with one another
     * No order-of-run dependency => They should pass or fail the same way in suite or when run individually.
     */
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class Isolation {


        @Order(0)
        @Test
        void addShouldReturnTheSumOfTwoPositiveNumbers() {
            //org.assertj.core.api.Assertions.fail("");
            resultOne = calculator.add(b, resultOne);
            assertThat(resultOne).isEqualTo(55);
        }

        @Order(1)
        @Test
        void givenTwoPositiveIntegers_whenMultiply_thenCorrectResult() {
            resultOne = calculator.multiply(a, resultOne);
            assertThat(resultOne).isEqualTo(605);
        }
    }

    /**
     * No matter how often or where you run it, it should produce the same result (Deterministic results ).
     * Each test should setup or arrange it's own data.
     * What if a set of tests need some common data? Use Data Helper classes that can setup this data for re-usability.
     * Good occasion to use un-memory DB
     */
    @Nested
    class Repeatable {

        List<String> lines = Arrays.asList("Hello", "all");
        MyFileReader fileReader = Mockito.mock(MyFileReader.class);

        @BeforeEach
        void beforeEach() {
            Mockito.when(fileReader.read(ArgumentMatchers.anyString())).thenReturn(lines);
        }

        @Test
        void addShouldReturnLinesOfAGivenFile() {
            List<String> result = fileReader.read("data.txt");
            assertThat(result)
                    .isNotNull()
                    .isNotEmpty()
                    .hasSize(lines.size())
                    .containsExactlyInAnyOrderElementsOf(lines);
        }
    }


    /**
     * hat it means is that running your test leaves it perfectly clear whether it passed or failed.
     * JUnit does this and fails with red, which lets you red-green-refactor.
     * By using a testing framework like JUnit, utilizing assertion libraries, and writing specific tests,
     * you can ensure that if a test fails, there will be clear and unambiguous reporting that tells you exactly what passed or failed.
     */
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class SelfValidating {

        @Order(0)
        @Test
        void addShouldReturnTheSumOfTwoPositiveNumbers() {
            resultTwo = calculator.add(b, a);
            assertThat(resultTwo).isEqualTo(a + b);
        }

        @Order(1)
        @Test
        void givenCalculator_whenMultiplyTwoPositiveNumbers_thenCorrectResult() {

            resultTwo = calculator.multiply(b, a);
            assertThat(resultTwo).isEqualTo(a * b);
        }
    }


    /**
     * ==> Timely
     * Practically, You can write unit tests at any time.
     * You can wait up to code is production-ready or you’re better off focusing on writing unit tests in a timely fashion.
     * The idea was that your tests should be written as close to when you write your code as possible (TDD: Before the code).
     * ==>  Thorough
     * Should cover every use case scenario and NOT just aim for 100% coverage.
     * Tests for large data sets - this will test runtime and space complexity.
     * Tests for security with users having different roles - behavior may be different based on user's role.
     * Tests for large values - overflow and underflow errors for data types like integer.
     * Tests for exceptions and errors.
     * Tests for illegal arguments or bad inputs.
     */
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ThoroughAndTimely {

        public static final String ORANGE = "ORANGE";
        public static final String FREE = "FREE";
        public static final String EXPRESSO = "EXPRESSO";
        public static final String PROMOBILE = "PROMOBILE";
        public static final String REGEX = "^(\\+221|00221){0,1}(78|77|76|75|70){1}([0-9]{7})$";
        String indicatif, operator;
        String number = "9876543";
        String template = "%s%s%s";
        String phone ;

        String getMobileOperator(String phone) throws BadPhoneException {
            String result="";
            Pattern r = Pattern.compile(REGEX);
            Matcher matcher = r.matcher(phone);
            if(matcher.matches()){
                switch (operator){
                    case "77":
                    case "78":
                        result= ORANGE;
                        break;

                    case "76":
                        result= FREE;
                        break;
                    case "70":
                        result= EXPRESSO;
                        break;
                    case "75":
                        result= PROMOBILE;
                        break;

                }
            }else{
                throw new BadPhoneException();
            }
            return result;
        }


        @Test
        void getOperator_withPlusIndicatifAnd77_shouldReturnOrange() {
            indicatif = "+221";
            operator = "77";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(ORANGE);
        }

        @Test
        void getOperator_withPlusIndicatifAnd78_shouldReturnOrange() {
            indicatif = "+221";
            operator = "78";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(ORANGE);
        }

        @Test
        void getOperator_with00IndicatifAnd77_shouldReturnOrange() {
            indicatif = "00221";
            operator = "77";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(ORANGE);
        }

        @Test
        void getOperator_with00IndicatifAnd78_shouldReturnOrange() {
            indicatif = "00221";
            operator = "78";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(ORANGE);
        }

        @Test
        void getOperator_withoutIndicatifAnd77_shouldReturnOrange() {
            indicatif = "";
            operator = "77";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(ORANGE);
        }

        @Test
        void getOperator_withoutIndicatifAnd78_shouldReturnOrange() {
            indicatif = "";
            operator = "78";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(ORANGE);
        }

        @Test
        void getOperator_withPlusIndicatifAnd76_shouldReturnFree() {
            indicatif = "+221";
            operator = "76";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(FREE);
        }

        @Test
        void getOperator_with00IndicatifAnd76_shouldReturnFree() {
            indicatif = "00221";
            operator = "76";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(FREE);
        }

        @Test
        void getOperator_withoutIndicatifAnd76_shouldReturnFree() {
            indicatif = "";
            operator = "76";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(FREE);
        }

        @Test
        void getOperator_withPlusIndicatifAnd70_shouldReturnExpresso() {
            indicatif = "+221";
            operator = "70";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(EXPRESSO);
        }

        @Test
        void getOperator_with00IndicatifAnd70_shouldReturnExpresso() {
            indicatif = "00221";
            operator = "70";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(EXPRESSO);
        }

        @Test
        void getOperator_withoutIndicatifAnd70_shouldReturnExpresso() {
            indicatif = "";
            operator = "70";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(EXPRESSO);
        }

        @Test
        void getOperator_withPlusIndicatifAnd75_shouldReturnPromobile() {
            indicatif = "+221";
            operator = "75";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(PROMOBILE);
        }

        @Test
        void getOperator_with00IndicatifAnd75_shouldReturnPromobile() {
            indicatif = "00221";
            operator = "75";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(PROMOBILE);
        }

        @Test
        void getOperator_withoutIndicatifAnd75_shouldReturnPromobile() {
            indicatif = "";
            operator = "75";
            phone =String.format(template, indicatif, operator, number);
            String result = getMobileOperator(phone);
            assertThat(result).isEqualTo(PROMOBILE);
        }

        @Test
        void getOperator_withBadIndicatif_shouldThrowError() {
            indicatif = "+222";
            operator = "77";
            phone =String.format(template, indicatif, operator, number);
            assertThrows(
                    BadPhoneException.class,
                    () -> getMobileOperator(phone)
            );
        }

        @Test
        void getOperator_withBadOperator_shouldThrowError() {
            indicatif = "+221";
            operator = "79";
            phone =String.format(template, indicatif, operator, number);
            assertThrows(
                    BadPhoneException.class,
                    () -> getMobileOperator(phone)
            );
        }

        @Test
        void getOperator_withNumberLessThan7digits_shouldThrowError() {
            indicatif = "+221";
            operator = "77";
            phone =String.format(template, indicatif, operator, number.substring(0, 5));
            assertThrows(
                    BadPhoneException.class,
                    () -> getMobileOperator(phone)
            );
        }

        @Test
        void getOperator_withNumberMorThan7digits_shouldThrowError() {
            indicatif = "+221";
            operator = "77";
            phone =String.format(template, indicatif, operator, number.concat("2"));
            assertThrows(
                    BadPhoneException.class,
                    () -> getMobileOperator(phone)
            );
        }

        @Test
        void getOperator_withBadNumber_shouldThrowError() {
            indicatif = "+221";
            operator = "77";
            phone =String.format(template, indicatif, operator, number.replace("9", "n"));
            assertThrows(
                    BadPhoneException.class,
                    () -> getMobileOperator(phone)
            );
        }

    }
}