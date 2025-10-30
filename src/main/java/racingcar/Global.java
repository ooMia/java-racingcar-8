package racingcar;

import racingcar.util.Console;
import racingcar.util.Tokenizer;
import racingcar.util.exception.ExceptionHandler;

public class Global {
    public static final Console CONSOLE = new Console() {
        @Override
        public String readLine() {
            return camp.nextstep.edu.missionutils.Console.readLine();
        }
    };

    private static final String ERROR_PREFIX = "[ERROR] ";
    public static final ExceptionHandler EXCEPTION_HANDLER = new ExceptionHandler(CONSOLE, ERROR_PREFIX);

    private static final char DEFAULT_DELIMITER = ',';
    public static final Tokenizer TOKENIZER = new Tokenizer(DEFAULT_DELIMITER);
}
