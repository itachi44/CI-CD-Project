package sn.ept.git.seminaire.cicd.demo;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

@Slf4j
class D_AssertJMatchersTest {


    @Test
    void test_string_matchers() {
        String name = "Sagesse devoir";
        assertThat(name)
                .as("If test fails display this message.")
                .isEqualTo("Sagesse devoir")
                .isEqualToIgnoringCase("Sagesse DEVOIR")
                .startsWith("S")
                .doesNotStartWith("age")
                .endsWith("r")
                .doesNotEndWith("re")
                .containsIgnoringCase("Devoir");
    }

    @Test
    void test_list_matchers() {
        List<String> list = Arrays.asList("Java", "Python", "Scala");
        assertThat(list)
                .hasSize(3)
                .contains("Java", "Scala")
                .contains("Java", Index.atIndex(0))
                .contains("Python", Index.atIndex(1))
                .contains("Scala", Index.atIndex(2))
                .doesNotContain("PHP")
                .allMatch(item->item.length()>=4)
                .anyMatch(item->item.length()<5)
                .noneMatch(item->item.length()>10);

    }

    @Test
    void test_map_matchers() {
        Map<String, String> map = new HashMap<>();
        map.put("nom", "DIC 2");
        map.put("departement", "GIT");

        assertThat(map)
                .hasSize(2)
                .containsOnlyKeys("nom","departement")
                .containsKey("nom")
                .doesNotContainKey("key")
                .doesNotContainKeys("key1","key2")
                .doesNotContainEntry("key","value")
                .doesNotContainValue("PFE")
                .extractingByKey("nom", as(InstanceOfAssertFactories.STRING))
                .isEqualToIgnoringCase("DIC 2")
                .startsWith("D")
                .doesNotStartWith("ept");
    }


    @Test
    void test_exception_matchers() {
        ICalculator calculator = new Calculator();
        assertThatThrownBy(() -> calculator.divide(1, 0))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("zero")
                .hasMessage(Calculator.DIVIDE_BY_ZERO);
    }

    @Test
    void test_optional_matchers() {
        Optional<Integer> optional =Optional.ofNullable(Integer.MAX_VALUE);
        assertThat(optional)
                .isNotNull()
                .isNotEmpty() //same as isPresent
                .isPresent()
                .get()
                .isExactlyInstanceOf(Integer.class)
                .isEqualTo(Integer.MAX_VALUE);
    }

}