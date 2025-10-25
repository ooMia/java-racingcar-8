package racingcar;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import racingcar.car.RacingCar;
import racingcar.view.OutputView;

public class RacingGame {
    private final List<RacingCar> racingCars;

    public RacingGame(GameInfo info) {
        this.racingCars = uniqueRacingCars(info.carNames());
    }

    private static List<RacingCar> uniqueRacingCars(List<String> carNames) {
        // self-assigned rule
        // TODO: handling uppercase difference
        if (Set.copyOf(carNames).size() != carNames.size()) {
            throw GlobalProblem.CAR_NAME_DUPLICATE.exception();
        }
        return carNames.stream().map(RacingCar::new).toList();
    }

    public void iterateSingleLap() {
        for (var car : this.racingCars) {
            car.move();
        }
    }

    public String getCurrentWinners() {
        var winnerNames = getWinners(this.racingCars);
        return OutputView.gameWinners(winnerNames);
    }

    static List<String> getWinners(List<RacingCar> racingCars) {
        var comparator = RacingCar.comparator().reversed();
        var sortedRacingCars = racingCars.stream().sorted(comparator).toList();
        var firstPrizeCar = sortedRacingCars.getFirst();

        var winnerNameList = new ArrayList<String>();
        for (var car : sortedRacingCars) {
            if (isTargetLostToOpponent(car, firstPrizeCar)) {
                break;
            }
            winnerNameList.add(car.name);
        }
        return winnerNameList;
    }

    private static boolean isTargetLostToOpponent(RacingCar target, RacingCar opponent) {
        return RacingCar.comparator().compare(target, opponent) < 0;
    }

    @Override
    public String toString() {
        return OutputView.racingGame(this.racingCars);
    }

    public record GameInfo(List<String> carNames) {

    }
}
