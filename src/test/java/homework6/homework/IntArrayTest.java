package homework6.homework;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IntArrayTest {
    private static IntArray intArray;
    private static final Random random = new Random();
    private static int [] arrayWithError = null;

    @BeforeEach
    void setUp() {
        intArray = new IntArray();
        arrayWithError = new int[]{1,2,3,5,6,7};
    }

    @MethodSource("arrayForFourTest")
    @ParameterizedTest
    void checkArrayForFours(int[] array) {
        int[] testArray = intArray.checkArrayForFours(array);
        for (int i = 0; i < testArray.length; i++) {
            Assertions.assertNotEquals(4, testArray[i]);
        }
    }

    @Test
    void checkArrayForFoursWithException(){
        Assertions.assertThrows(RuntimeException.class, () -> intArray.checkArrayForFours(arrayWithError));
    }

    private static Stream<Arguments> arrayForFourTest(){
        List<Arguments> arguments = new ArrayList<>();

        for (int i = 0; i < 4 ; i++) {
            int[] array = new int[random.nextInt(10)];
            for (int j = 0; j < array.length; j++) {
                array[j] = random.nextInt(10);
            }
            arguments.add(Arguments.arguments(array));
        }

        return arguments.stream();
    }
    @MethodSource("arrayForFourTest")
    @ParameterizedTest
    void checkArrayForFourAndOneTrue(int[] array) {
        Assertions.assertEquals(true,intArray.checkArrayForFourAndOne(array));
    }

    @MethodSource("arrayForFourTest")
    @ParameterizedTest
    void checkArrayForFourAndOneFalse(int[] array) {
        Assertions.assertEquals(false,intArray.checkArrayForFourAndOne(array));
    }
}