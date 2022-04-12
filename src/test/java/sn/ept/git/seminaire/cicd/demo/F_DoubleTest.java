package sn.ept.git.seminaire.cicd.demo;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.AdditionalAnswers.returnsSecondArg;


@Slf4j
@ExtendWith(MockitoExtension.class)
class F_DoubleTest {

    //annotations @Mock,@Spy, ... or static methods
    private ICalculator mockCalculator = mock(ICalculator.class);
    // private ICalculator spyCalculator = spy(ICalculator.class);

    @Mock
    private List<String> mockList;

    @Spy
    private List<String> spyList = new ArrayList<>();

    private double a, b;
    String item ="test data";

    @BeforeEach
    void beforeEach() {
        log.info("Something to do before each test");
        a = 11;
        b = 22;
    }

    @Test
    void test_then() {
        when(mockCalculator.add(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble()))
        .then(returnsFirstArg());
        double provided = mockCalculator.add(a, b);
        double expected = a;
        Assertions
                .assertThat(provided)
                .isEqualTo(expected);
    }


    @Test
    void test_thenReturn() {
        when(mockCalculator.add(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble()))
                .thenReturn(0.0);
        double provided = mockCalculator.add(a, b);
        double expected = 0.0;
        Assertions.assertThat(provided).isEqualTo(expected);
    }

    @Test
    void test_verify() {
        when(mockCalculator.add(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble()))
                .then(returnsSecondArg());
        mockCalculator.add(a, b);
        verify(mockCalculator, times(1))
                .add(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble());
    }


    @Test
    void test_thenAnswer() {
        when(mockCalculator.add(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble()))
                .thenAnswer(new Answer<Double>() {
                    //please replace with lambda
                    //not : mocks do not ame at implementing logic
                    @Override
                    public Double answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return Double.parseDouble(args[0].toString())+Double.parseDouble(args[1].toString());
                    }
                });
        double provided = mockCalculator.add(a, b);
        double expected = a+b;
        Assertions
                .assertThat(provided)
                .isEqualTo(expected);
    }

    @Test
    void test_thenThrow() {
        when(mockCalculator.divide(ArgumentMatchers.anyDouble(), ArgumentMatchers.eq(0.0)))
                .thenThrow(ArithmeticException.class);
        assertThrows(
                ArithmeticException.class,
                () -> mockCalculator.divide(a, 0.0)
        );
    }

    /**
     * mock complete replacement. By default, calling the methods of mock object will do nothing
     */
    @Test
    void testMockList() {
        mockList.add(item);
        Assertions
                .assertThat(mockList)
                .hasSize(0);
        verify(mockList).add(item);
    }

    /**
     * spy  => partial mock (part of the object will be mocked and part will use real method invocations)
     * spy object will call the real method when not stub
     *
     * ------------------------------
     * When shoud we use mock or spy?
     * ------------------------------
     * Mock : If you want to avoid  calling external services and just want to test the logic inside of the unit,
     * Spy : If you want to run the program as it is and just stub specific methods.
     *
     */
    @Test
    public void testSpyList() {
        spyList.add(item);
        verify(spyList).add(item);
        Assertions
                .assertThat(spyList)
                .hasSize(1).containsExactly(item);
    }

    @Test
    public void testMockWithStub() {
        //stubbing a method
        when(mockList.get(100)).thenReturn(item);
        Assertions
                .assertThat(mockList.get(100))
                .isEqualTo(item);

    }

    @Test
    public void testSpyWithStub() {
        //stubbing a spy method will result the same as the mock object
        //take note of using doReturn instead of when
        doReturn(item).when(spyList).get(300);
        Assertions
                .assertThat(spyList.get(300))
                .isEqualTo(item);
    }

}