package racingcar.view;

import java.util.Arrays;
import java.util.regex.Pattern;

import camp.nextstep.edu.missionutils.Console;
import racingcar.GlobalProblem;
import racingcar.RacingGame.GameInfo;

public final class InputView {

    private static final Pattern commaSeparator = Pattern.compile("\\s*,\\s*", Pattern.UNICODE_CHARACTER_CLASS);

    private InputView() {
    }

    public static GameInfo inputGameInfo() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String csvCarNames = Console.readLine();
        var carNames = commaSeparator.split(csvCarNames);
        var names = Arrays.stream(carNames).filter(name -> !name.isEmpty()).toList();
        return new GameInfo(names);
    }

    public static int inputGameIteration() {
        try {
            System.out.println("시도할 횟수는 몇 회인가요?");
            String number = Console.readLine();
            var iteration = Long.parseLong(number);
            return Math.toIntExact(iteration);
        } catch (NumberFormatException e) {
            throw GlobalProblem.FAILED_TO_PARSE_LONG.exception(e);
        } catch (ArithmeticException e) {
            throw GlobalProblem.INTEGER_OUT_OF_BOUND.exception(e);
        }
    }
}
