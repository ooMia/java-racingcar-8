package racingcar.util.exception;

public interface BaseProblem {
    BaseRuntimeException exception();

    BaseRuntimeException exception(Throwable cause);
}
