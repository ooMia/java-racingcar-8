package racingcar.game;

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
        // self-assigned rule: name cannot be duplicated
        if (Set.copyOf(carNames).size() != carNames.size()) {
            throw GameProblem.CAR_NAME_DUPLICATE.exception();
        }
        var result = carNames.stream().map(RacingCar::new).toList();
        if (result.isEmpty()) {
            throw GameProblem.NO_CAR_EXISTS.exception();
        }
        return result;
    }

    // TEST-PURPOSE ONLY
    RacingGame(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
    }

    /**
     * Defines a single lap iteration.
     * This is the only way to change the state of the game.
     */
    public void iterateSingleLap() {
        for (var car : this.racingCars) {
            car.move();
        }
    }

    /**
     * @return a string representing the current winners of the game
     */
    public String getCurrentWinners() {
        var winnerNames = getWinners();
        return OutputView.gameWinners(winnerNames);
    }

    List<String> getWinners() {
        var comparator = RacingCar.comparator().reversed();
        var sortedRacingCars = racingCars.stream().sorted(comparator).toList();
        var firstPrizeOpponent = sortedRacingCars.getFirst();

        var winnerNameList = new ArrayList<String>();
        for (var target : sortedRacingCars) {
            if (isTargetLostToOpponent(target, firstPrizeOpponent)) {
                break;
            }
            winnerNameList.add(target.name);
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

    public record GameIteration(int positiveInteger) {
        public GameIteration {
            if (positiveInteger < 0) {
                throw GameProblem.ITERATION_NEGATIVE.exception();
            }
        }
    }
}
