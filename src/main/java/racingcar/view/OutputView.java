package racingcar.view;

import racingcar.car.RacingCar;
import racingcar.game.RacingGame;
import racingcar.game.RacingGame.GameIteration;

public final class OutputView {

    private OutputView() {
    }

    public static String racingCar(String name, int distance) {
        var status = new StringBuilder(name);
        status.append(" : ");
        status.repeat('-', distance);
        return status.toString();
    }

    public static String racingGame(Iterable<RacingCar> racingCars) {
        var status = new StringBuilder();
        for (var car : racingCars) {
            status.append(car.toString()).append(System.lineSeparator());
        }
        return status.toString();
    }

    public static String gameWinners(Iterable<String> carNames) {
        return String.join(", ", carNames);
    }

    public static void printRunIterationLog(RacingGame racingGame, GameIteration iteration) {
        System.out.println("실행 결과");
        int numberIteration = iteration.positiveInteger();
        while (numberIteration-- > 0) {
            racingGame.iterateSingleLap();
            System.out.println(racingGame);
        }
    }

    public static void printRunWinnerResult(RacingGame racingGame) {
        String winners = racingGame.getCurrentWinners();
        System.out.printf("최종 우승자 : %s", winners);
        System.out.println();
    }

}
