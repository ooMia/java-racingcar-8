package racingcar.game;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.Set;
import racingcar.car.RacingCar;

public class RacingGame {
    private final List<RacingCar> racingCars;

    public RacingGame(List<String> carNames) {
        this.racingCars = uniqueRacingCars(carNames);
    }

    private List<RacingCar> uniqueRacingCars(List<String> carNames) {
        if (Set.copyOf(carNames).size() != carNames.size()) {
            throw GameProblem.DUPLICATE_CAR_NAME.exception();
        }
        return carNames.stream().map(RacingCar::new).toList();
    }

    public void iterateSingleLap() {
        for (var car : this.racingCars) {
            int randomValue = Randoms.pickNumberInRange(0, 9);
            car.conditionalMove(randomValue);
        }
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (var car : this.racingCars) {
            sb.append(car.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
