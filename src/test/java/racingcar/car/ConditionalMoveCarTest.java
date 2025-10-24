package racingcar.car;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ConditionalMoveCarTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    private RacingCar car;

    @BeforeEach
    void setUp() {
        car = new RacingCar("pobi");
    }

    @Test
    void instantiateFailWithNull() {
        assertThrows(IllegalArgumentException.class, () -> new RacingCar(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "abcdef", "가나다라마사"})
    void instantiateFailWithInvalidNames(String name) {
        assertThrows(IllegalArgumentException.class, () -> new RacingCar(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"가나다라마", "abcde"})
    void instantiateSuccessWithValidNames(String name) {
        assertDoesNotThrow(() -> new RacingCar(name));
    }

    @Test
    void moveFailWhenArgumentOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> car.conditionalMove(-1));
        assertThrows(IllegalArgumentException.class, () -> car.conditionalMove(10));
    }

    @Test
    void moveDistanceToOneWhenValueGreaterThanFour() {
        int expectDistance = 1;
        car.conditionalMove(MOVING_FORWARD);
        assertEquals(expectDistance, car.distance);
    }

    @Test
    void moveDistanceToZeroWhenValueLessThanFour() {
        int expectDistance = 0;
        car.conditionalMove(STOP);
        assertEquals(expectDistance, car.distance);
    }

    @Test
    @Disabled("move() append and store its status in StringBuilder, so try again after departing its status")
    void moveFailedWhenIterationOverMaxInteger() {
        var car = new RacingCar("pobi", Integer.MAX_VALUE);
        assertThrows(IllegalArgumentException.class, () -> car.conditionalMove(MOVING_FORWARD));
    }

    @Test
    void carStringIncreaseWhenMoved() {
        var car = new RacingCar("pobi", 3);
        car.conditionalMove(MOVING_FORWARD);

        String expect = "pobi : ----";
        assertEquals(expect, car.toString());
    }

    @Test
    void carStringNotChangeWhenStopped() {
        var car = new RacingCar("pobi", 3);
        car.conditionalMove(STOP);

        String expect = "pobi : ---";
        assertEquals(expect, car.toString());
    }
}