package homework6.Lesson;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator;
    Battery battery;

    @BeforeEach
    void setUp() {

        battery = Mockito.mock(Battery.class);
        calculator = new Calculator();
    }


    @Test
    @Disabled
    void sum() {
        Assertions.assertEquals(10, calculator.sum(3, 7), "Sum should be equal 10");
    }

    @Test
//    @Disabled
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void multiply() {
        Assertions.assertEquals(15, calculator.multiply(3, 5));
    }

    @Test
    void division(){
        Assertions.assertEquals(5,calculator.division(10,2));
    }

    @Test
    void divisionWithException() {
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.division(10, 0));
    }

    @CsvSource({
            "1,2,3",
            "3,2,5",
            "13,2,15"
    })
    @ParameterizedTest
    void testSumMulti(int a, int b , int result){
    Assertions.assertEquals(result,calculator.sum(a,b));
    }


    @MethodSource("dataForAddition")
    @ParameterizedTest
    void testSumMultiWithMethod(int a, int b , int result){
        Assertions.assertEquals(result,calculator.sum(a,b));
    }

    public static Stream<Arguments> dataForAddition(){
        List<Arguments> arguments = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int a = random.nextInt(1000);
            int b = random.nextInt(1000);
            arguments.add(Arguments.arguments(a,b, a + b ));
        }

        return arguments.stream();
    }
    @Test
    void testWithBattery(){
        Mockito.doReturn(50).when(battery).getPercent();
   }
}
