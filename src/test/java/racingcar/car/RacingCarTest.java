package racingcar.car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.car.RacingCar.MoveRule;

public class RacingCarTest {

    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;
    private RacingCar defaultBehaviorCar;

    // public helper method for testing
    public static RacingCar getRacingCarByNameAndDistance(String name, int distance) {
        return new RacingCar(name, distance);
    }

    @BeforeEach
    void setUp() {
        defaultBehaviorCar = new RacingCar("pobi");
    }

    @Test
    void testForwardFailWhenArgumentOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> defaultBehaviorCar.forward(-1));
        assertThrows(IllegalArgumentException.class, () -> defaultBehaviorCar.forward(10));
    }

    @Test
    void testForwardWhenValueGreaterThanFourAsDefault() {
        assertTrue(defaultBehaviorCar.forward(MOVING_FORWARD));
    }

    @Test
    void testForwardWhenValueLessThanFourAsDefault() {
        assertFalse(defaultBehaviorCar.forward(STOP));
    }

    @Test
    void testForwardFailedWhenIterationOverMaxInteger() {
        var car = new RacingCar("pobi", Integer.MAX_VALUE);
        assertThrows(IllegalArgumentException.class, () -> car.forward(MOVING_FORWARD));
    }

    @Test
    void testCarStringIncreaseWhenMoved() {
        var car = new RacingCar("pobi", 3);
        car.forward(MOVING_FORWARD);

        String expect = "pobi : ----";
        assertEquals(expect, car.toString());
    }

    @Test
    void testCarStringNotChangeWhenStopped() {
        var car = new RacingCar("pobi", 3);
        car.forward(STOP);

        String expect = "pobi : ---";
        assertEquals(expect, car.toString());
    }

    @Test
    void testCustomMoveRule() {
        MoveRule customRule = new RacingCar.MoveRule(7, 6, 8);
        RacingCar customMoveCar = RacingCar.builder().name("asd").moveRule(customRule).build();

        assertTrue(customMoveCar.forward(7));
        assertFalse(customMoveCar.forward(6));

        assertThrows(IllegalArgumentException.class, () -> customMoveCar.forward(5));
        assertThrows(IllegalArgumentException.class, () -> customMoveCar.forward(9));
    }

    @Test
    void testCustomNameLengthRule() {
        NamedCar.NameLengthRule customRule = new NamedCar.NameLengthRule(1, 2);
        assertThrows(IllegalArgumentException.class,
                () -> RacingCar.builder().name("asd").nameLengthRule(customRule).build());
    }

}