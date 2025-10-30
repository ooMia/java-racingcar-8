package racingcar.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarImplTest {

    private CarImpl car;

    @BeforeEach
    void setUp() {
        car = new CarImpl("test");
    }

    @Test
    void testForward() {
        assertTrue(car.forward(4));
        assertEquals(1, car.distance);
    }

    @Test
    void testToString() {
        var car = createCarWithDistance("test", 3);
        String result = car.toString();
        assertEquals("test : ---", result);
    }

    public static Car createCarWithDistance(String name, int distance) {
        CarImpl car = new CarImpl(name);
        car.distance = distance;
        return car;
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "AB", "ABC", "ABCD", "ABCDE"})
    void testNameValidationSuccess(String name) {
        assertDoesNotThrow(() -> CarImpl.NameRule.DEFAULT.validate(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "ABCDEF"})
    void testNameValidationFail(String name) {
        assertThrows(IllegalArgumentException.class, () -> CarImpl.NameRule.DEFAULT.validate(name));
    }
}
