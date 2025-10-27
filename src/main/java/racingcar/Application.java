package racingcar;

import racingcar.game.RacingGame;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        var gameInfo = InputView.inputGameInfo();
        var racingGame = new RacingGame(gameInfo);
        var numberIteration = InputView.inputGameIteration();

        OutputView.printRunIterationLog(racingGame, numberIteration);
        OutputView.printRunWinnerResult(racingGame);
    }
}
