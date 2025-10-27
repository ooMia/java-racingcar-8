package racingcar.car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.car.CarBuilder.RacingCarBuilder;
import racingcar.car.RacingCar.MoveRule;

public class RacingCarTest {

    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;
    private RacingCar defaultBehaviorCar;

    private RacingCarBuilder builder;

    // public helper method for testing
    public static RacingCar getRacingCarByNameAndDistance(String name, int distance) {
        return new RacingCar(name, distance);
    }

    @BeforeEach
    void setUp() {
        this.builder = new RacingCarBuilder().name("pobi");
        this.defaultBehaviorCar = builder.build();
    }

    @Test
    void testCustomMoveRule() {
        MoveRule customRule = new RacingCar.MoveRule(6, 7, 8);
        RacingCar customMoveCar = builder.moveRule(customRule).build();

        assertTrue(customMoveCar.forward(7));
        assertFalse(customMoveCar.forward(6));

        assertThrows(IllegalArgumentException.class, () -> customMoveCar.forward(5));
        assertThrows(IllegalArgumentException.class, () -> customMoveCar.forward(9));
    }

    @Test
    void testBuildRacingCarWithCustomNameLengthRule() {
        NamedCar.NameLengthRule customRule = new NamedCar.NameLengthRule(1, 2);
        assertThrows(IllegalArgumentException.class, () -> builder.nameLengthRule(customRule).build());
    }

    @Nested
    class DefaultBehaviorCarForwardAtCertainDistanceTest {

        @Test
        void testForwardFailedWhenIterationOverMaxInteger() {
            RacingCar carAtMaximumDistance = defaultBehaviorCarAtDistance(Integer.MAX_VALUE);
            assertThrows(IllegalArgumentException.class, () -> carAtMaximumDistance.forward(MOVING_FORWARD));
        }

        private RacingCar defaultBehaviorCarAtDistance(int distance) {
            return new RacingCar("pobi", distance);
        }

        @Test
        void testCarStringIncreaseWhenMoved() {
            RacingCar carAtDistance3 = defaultBehaviorCarAtDistance(3);
            int beforeLength = carAtDistance3.toString().length();

            carAtDistance3.forward(MOVING_FORWARD);
            int afterLength = carAtDistance3.toString().length();

            assertEquals(afterLength, beforeLength + 1);
        }

        @Test
        void testCarStringNotChangeWhenStopped() {
            RacingCar carAtDistance3 = defaultBehaviorCarAtDistance(3);
            int beforeLength = carAtDistance3.toString().length();

            carAtDistance3.forward(STOP);
            int afterLength = carAtDistance3.toString().length();

            assertEquals(afterLength, beforeLength);
        }
    }

    @Nested
    class DefaultBehaviorCarForwardTest {

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
    }

}
