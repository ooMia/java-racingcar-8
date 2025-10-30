package racingcar;

import racingcar.util.exception.BaseRuntimeException;

enum PresentationProblem implements racingcar.util.exception.BaseProblem {
    SPLIT_STRING_FAILED,
    PARSE_INTEGER_FAILED;

    private final String message;

    PresentationProblem() {
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
