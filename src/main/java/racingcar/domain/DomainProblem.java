package racingcar.domain;

import racingcar.util.exception.BaseRuntimeException;

public enum DomainProblem implements racingcar.util.exception.BaseProblem {
    INVALID_CAR_NAME,
    INVALID_TRY_COUNT, UNSUPPORTED_COMPARISON, EMPTY_CARS;

    private final String message;

    DomainProblem() {
        this.message = this.name();
    }

    @Override
    public BaseRuntimeException exception() {
        throw new BaseRuntimeException(this.message);
    }

    @Override
    public BaseRuntimeException exception(Throwable cause) {
        throw new BaseRuntimeException(this.message, cause);
    }
}
