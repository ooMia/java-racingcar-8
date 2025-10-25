package racingcar.car;

enum CarProblem {

    MOVE_ARGUMENT_OUT_OF_RANGE,
    FORWARD_EXCEED_LIMIT,
    CAR_NAME_OUT_OF_BOUND,
    CAR_NAME_NON_NULL_CONSTRAINT;

    private final String message;

    CarProblem() {
        this.message = this.name();
    }

    IllegalArgumentException exception() {
        return new IllegalArgumentException(this.message);
    }

    IllegalArgumentException exception(ArithmeticException e) {
        return new IllegalArgumentException(this.message, e);
    }
}
