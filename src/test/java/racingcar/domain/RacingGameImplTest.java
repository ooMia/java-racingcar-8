package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatList;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RacingGameImplTest {

    private List<String> carNames;
    private List<Integer> distances;
    private List<Car> cars;
    private RacingGameImpl racingGame;

    @BeforeEach
    void setUp() {
        carNames = List.of("A", "B", "C");
        distances = List.of(1, 3, 3);
        cars = new java.util.ArrayList<>();
        for (int i = 0; i < carNames.size(); i++) {
            Car car = CarImplTest.createCarWithDistance(carNames.get(i), distances.get(i));
            cars.add(car);
        }
        racingGame = new RacingGameImpl();
        racingGame.cars = cars;
    }

    @Test
    void testCurrentState() {
        RacingGameImpl.State state = racingGame.currentState();
        assertThatList(state.carStates())
                .containsAll(List.of("A : -", "B : ---", "C : ---"));
    }

    @Test
    void testWinners() {
        RacingGameImpl.Winner winner = RacingGameImpl.Winner.of(cars);
        assertThatList(winner.carNames()).containsExactly("B", "C");
    }
}
