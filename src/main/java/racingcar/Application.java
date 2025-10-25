package racingcar;

import java.util.Arrays;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import racingcar.game.GameWinners;
import racingcar.game.RacingGame;

public class Application {

    // TODO: 혼잡하다 혼잡해
    // 각 객체에게 무언가를 전달하면 그 형식대로 출력을 해줄 수는 없을까?
    // 출력의 규칙을 정의하기 위해 필요한 것이 있다면?
    public static void main(String[] args) {
        // TODO: refactor
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String carNamesSplitByComma = Console.readLine();
        var carNames = carNamesSplitByComma.split("\\s*,\\s*");
        List<String> names = Arrays.stream(carNames).filter(name -> !name.isEmpty()).toList();
        var racingGame = new RacingGame(names);

        System.out.println("시도할 횟수는 몇 회인가요?");
        String number = Console.readLine();
        long iteration = Long.parseLong(number);

        System.out.println("실행 결과");
        while (iteration-- > 0) {
            racingGame.iterateSingleLap();
            System.out.println(racingGame);
        }

        GameWinners winners = racingGame.getWinners();
        System.out.printf("최종 우승자 : %s%s", winners.toString(), System.lineSeparator());
    }
}
