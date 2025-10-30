package racingcar.util.exception;

import racingcar.util.Console;

public class ExceptionHandler {
    private final String errorPrefix;
    private final Console console;

    public ExceptionHandler(Console console, String errorPrefix) {
        this.console = console;
        this.errorPrefix = errorPrefix;
    }

    public Handler of(BaseProblem cause) {
        return new Handler(this.console, cause);
    }

    public <T> T throwIfInvalid(SupplierWithBaseException<T> supplier) throws BaseRuntimeException {
        try {
            return supplier.get();
        } catch (BaseException e) {
            throw new BaseRuntimeException(e);
        }
    }

    public <T> T tryUntilValid(SupplierWithBaseException<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (BaseException e) {
                console.printLine(e.getMessage());
            }
        }
    }

    @FunctionalInterface
    public interface SupplierWithBaseException<T> {
        T get() throws BaseException;
    }

    public class Handler {
        private final Console console;
        private final BaseProblem cause;

        public Handler(Console console, BaseProblem cause) {
            this.console = console;
            this.cause = cause;
        }

        public Handler log() {
            this.console.printLine(errorPrefix + cause.toString());
            return this;
        }

        public void raiseException(Throwable e) throws BaseRuntimeException {
            throw cause.exception();
        }
    }
}