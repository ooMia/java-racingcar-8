package racingcar.game;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RacingGameTest {

    private RacingGame game;

    @BeforeEach
    void setUp() {
        var names = List.of("pobi", "woni", "jun");
        this.game = new RacingGame(names);
    }

    @Test
    void instantiateFailWhenCarNamesDuplicated() {
        // TODO: How about using upperCase letters?
        // var duplicatedNames = List.of("POBI", "pobi", "jun");

        var duplicatedNames = List.of("pobi", "pobi", "jun");
        assertThrows(IllegalArgumentException.class, () -> {
            this.game = new RacingGame(duplicatedNames);
        });
    }

    @Test
    @DisplayName("This test depends on randomize value")
    void iterateSingleLapIncreaseStringLength() {
        int incrementPerIteration = 3;
        int lowerBound = game.toString().length();
        int upperBound = lowerBound + incrementPerIteration;

        game.iterateSingleLap();

        int actual = game.toString().length();
        assertTrue(lowerBound <= actual);
        assertTrue(actual <= upperBound);
    }
}
