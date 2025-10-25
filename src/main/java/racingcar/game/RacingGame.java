package racingcar.game;

import java.util.ArrayList;
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
            car.move();
        }
    }

    public GameWinners getWinners() {
        return getWinners(this.racingCars);
    }

    // TODO: 이건 거의 GameWinner의 관심사 아닐까?
    // 근데 성능을 향상하려면 여기서 계속 상태를 관리하는 게 맞을지도?
    // 성능이 중요해, 역할 분리가 중요해? 둘도 만족시킬 방법은 없을까?
    static GameWinners getWinners(List<RacingCar> racingCars) {
        // TODO: enhance performance
        var comparator = RacingCar.comparator();
        var sortedRacingCars = racingCars.stream().sorted(comparator.reversed()).toList();
        var firstPrize = sortedRacingCars.getFirst();

        var winnerNameList = new ArrayList<String>();
        for (var car : sortedRacingCars) {
            if (comparator.compare(car, firstPrize) < 0) {
                break;
            }
            winnerNameList.add(car.name);
        }
        return new GameWinners(winnerNameList);
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
