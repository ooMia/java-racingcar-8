package racingcar.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import racingcar.car.RacingCar;
import racingcar.car.RacingCarTest;

class RacingGameTest {

    @Test
    void instantiateFailWhenCarNamesDuplicated() {
        var duplicatedNames = List.of("pobi", "pobi", "jun");
        var gameInfo = new RacingGame.GameInfo(duplicatedNames);

        assertThrows(IllegalArgumentException.class, () -> new RacingGame(gameInfo));
    }

    @Test
    void instantiateFailWhenNoNameProvided() {
        List<String> emptyName = List.of();
        var gameInfo = new RacingGame.GameInfo(emptyName);

        assertThrows(IllegalArgumentException.class, () -> new RacingGame(gameInfo));
    }

    @Test
    void partialWinnersWithEqualPrize() {
        var names = List.of("pobi", "woni", "jun");
        var distances = List.of(2, 1, 2);
        var game = gameFromListRacingCars(names, distances);

        var expected = List.of("pobi", "jun").toArray();
        assertArrayEquals(expected, game.getWinners().toArray());
    }

    private static RacingGame gameFromListRacingCars(List<String> names, List<Integer> distances) {
        List<RacingCar> cars = new ArrayList<>();
        for (int i = 0; i < names.size(); ++i) {
            var car = RacingCarTest.getRacingCarByNameAndDistance(names.get(i), distances.get(i));
            cars.add(car);
        }
        return new RacingGame(cars);
    }

    @Test
    void allWinnersWithZeroDistances() {
        var names = List.of("pobi", "woni", "jun");
        var distances = List.of(0, 0, 0);
        var game = gameFromListRacingCars(names, distances);

        var expected = List.of("pobi", "woni", "jun").toArray();
        assertArrayEquals(expected, game.getWinners().toArray());
    }
}
