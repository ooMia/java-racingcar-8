package racingcar.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.car.RacingCar;
import racingcar.car.RacingCarTest;
import racingcar.game.RacingGame.GameInfo;

class RacingGameTest {

    private RacingGame game;

    @BeforeEach
    void setUp() {
        this.game = gameFromListName(List.of("pobi", "woni", "jun"));
    }

    private static RacingGame gameFromListName(List<String> names) {
        var info = new GameInfo(names);
        return new RacingGame(info);
    }

    @Test
    void instantiateFailWhenCarNamesDuplicated() {
        var duplicatedNames = List.of("pobi", "pobi", "jun");
        assertThrows(IllegalArgumentException.class, () -> gameFromListName(duplicatedNames));
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

    @Test
    void getWinnerContainsSamePrizeWinners() {
        // TODO: refactor test
        var names = List.of("pobi", "woni", "jun");
        var distances = List.of(2, 1, 2);
        List<RacingCar> cars = createRacingCars(names, distances);

        var expected = List.of("pobi", "jun").toArray();
        assertArrayEquals(expected, RacingGame.getWinners(cars).toArray());
    }

    private static List<RacingCar> createRacingCars(List<String> names, List<Integer> distances) {
        List<RacingCar> result = new ArrayList<>();
        for (int i = 0; i < names.size(); ++i) {
            var car = RacingCarTest.getRacingCarByNameAndDistance(names.get(i), distances.get(i));
            result.add(car);
        }
        return result;
    }
}
