package racingcar;

import java.util.List;

import racingcar.domain.DomainService;
import racingcar.domain.RacingGame;
import racingcar.util.Console;
import racingcar.util.Tokenizer;

public interface Controller {

    void setup();

    void start();

    void finish();

}

class ControllerImpl implements Controller {

    private final Tokenizer tokenizer;
    private final Console console;
    private RacingGame game = null;

    public ControllerImpl(Tokenizer tokenizer, Console console) {
        this.tokenizer = tokenizer;
        this.console = console;
    }

    @Override
    public void setup() {
        console.printLine("경주할 자동차 이름을 입력하세요 (이름은 쉼표(,)로 구분)");
        String input = console.readLine();
        List<String> carNames = tokenizer.split(input);
        game = DomainService.createGame(carNames);
    }

    @Override
    public void start() {
        console.printLine("시도할 회수는 몇 회인가요?");
        int rounds = Global.EXCEPTION_HANDLER.throwIfInvalid(() -> console.readInt());
        console.printLine();
        console.printLine("실행 결과");
        while (rounds-- > 0) {
            console.printLine(game.play());
            console.printLine();
        }
    }

    @Override
    public void finish() {
        console.printLine(game.winners());
    }

}
