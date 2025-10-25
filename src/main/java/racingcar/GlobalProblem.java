package racingcar;

public enum GlobalProblem {
    INVALID_INPUT, FAILED_TO_PARSE_LONG, INTEGER_OUT_OF_BOUND, CAR_NAME_DUPLICATE;

    private final String message;

    GlobalProblem() {
        this.message = this.name();
    }

    public IllegalArgumentException exception() {
        return new IllegalArgumentException(this.message);
    }

    public IllegalArgumentException exception(Exception e) {
        return new IllegalArgumentException(this.message, e);
    }
}
